package com.mx.smarttools.admin.pizarron.mbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.mx.smarttools.admin.pizarron.mbean.lazy.ProyectoDataModel;
import com.mx.smarttools.admin.proyecto.model.Esfuerzo;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;

@ViewScoped
@ManagedBean
public class EdicionMB {

	@ManagedProperty(value="#{pizarronMB}")
	private PizarronMB pizarronMB;
	
	private int idProyecto;
	private String nombreProyecto;
	
	private Map<String, Object> filters;
	private List<Proyecto> proyectoList;
	
	private LazyDataModel<Proyecto> projectLazy;
	
	private Proyecto selectedProject;
	private Proyecto proyecto;
	private String menuOption;
	
	private List<HistoriasUsuario> selectHistToSprint;
	private List<HistoriasUsuario> historiasRows;

	@PostConstruct
    public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
                //FacesContext.getExternalContext().getRequestParameterMap();
		this.menuOption = params.get("option");
		
		filters = new HashMap<>();
		idProyecto = 0;
		selectedProject = null;
		proyecto = new Proyecto();
		proyecto.setHistoriasUsuarios(new ArrayList<HistoriasUsuario>());
		searchProject();
	}
	
	public void searchProject(){
		initFilters();
		proyectoList = getPizarronMB().getProyectoService().getProyectos();
		projectLazy = new ProyectoDataModel(proyectoList, filters);
	}
	
	public void initFilters(){
		if(this.idProyecto != 0)
			filters.put("proyectoId", Integer.valueOf(idProyecto));
		if(nombreProyecto != null)
			filters.put("nombreProyecto", nombreProyecto);
	}
	
	public void onRowSelect(SelectEvent event) {
		int projectId = ((Proyecto) event.getObject()).getProyectoId();
		proyecto = getPizarronMB().getProyectoService().fidProyecto(projectId);
		
		Map<String, String> queryParameter = new HashMap<String,String>();
		
		try{
			if(menuOption != null){
				if(this.menuOption.equals("sprints")){
					queryParameter.put("idEsfuerzo", Integer.toString(0));
					System.out.println("Opcion seleccionada: "+menuOption);
					historiasRows = getPizarronMB().getHistoriaService().getHistoriasByParameter(queryParameter);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error consulta historias ", 
					"Error al consultar lista de historias.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void onRowReorder(ReorderEvent event) {
		int consecutivo = 0;
		
		try {
			for(HistoriasUsuario story: proyecto.getHistoriasUsuarios()){
				story.setConsecutivo(consecutivo+1);
				getPizarronMB().getHistoriaService().updateHistoria(story);
				consecutivo++;
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Movimiento exitoso ", 
					"Movimiento de lista correcto.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en movimiento ", 
					"Error al mover elemento de la lista.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		
		
    }
	
	public void addSprint(){
		Esfuerzo esfuerzo = new Esfuerzo();
		esfuerzo.setProyectoFk(proyecto.getProyectoId());
	}
	
	public PizarronMB getPizarronMB() {
		return pizarronMB;
	}

	public void setPizarronMB(PizarronMB pizarronMB) {
		this.pizarronMB = pizarronMB;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public List<Proyecto> getProyectoList() {
		return proyectoList;
	}

	public void setProyectoList(List<Proyecto> proyectoList) {
		this.proyectoList = proyectoList;
	}

	public LazyDataModel<Proyecto> getProjectLazy() {
		return projectLazy;
	}

	public void setProjectLazy(LazyDataModel<Proyecto> projectLazy) {
		this.projectLazy = projectLazy;
	}

	public Proyecto getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Proyecto selectedProject) {
		this.selectedProject = selectedProject;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public String getMenuOption() {
		return menuOption;
	}

	public void setMenuOption(String menuOption) {
		this.menuOption = menuOption;
	}

	public List<HistoriasUsuario> getSelectHistToSprint() {
		return selectHistToSprint;
	}

	public void setSelectHistToSprint(List<HistoriasUsuario> selectHistToSprint) {
		this.selectHistToSprint = selectHistToSprint;
	}

	public List<HistoriasUsuario> getHistoriasRows() {
		return historiasRows;
	}

	public void setHistoriasRows(List<HistoriasUsuario> historiasRows) {
		this.historiasRows = historiasRows;
	}
}
