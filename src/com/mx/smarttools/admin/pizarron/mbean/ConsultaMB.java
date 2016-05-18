package com.mx.smarttools.admin.pizarron.mbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

import com.mx.smarttools.admin.pizarron.mbean.lazy.ProyectoDataModel;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@ViewScoped
@ManagedBean
public class ConsultaMB {
	
	@ManagedProperty(value="#{pizarronMB}")
	private PizarronMB pizarronMB;
	
	private List<Proyecto> proyectoList;
	
	private int idProyecto;
	private String nombreProyecto;
	
	private LazyDataModel<Proyecto> projectLazy;
	private Map<String, Object> filters;
	
	Proyecto selectedProject;
	Proyecto proyecto;
	
	private TreeNode treeProj;
	
	@PostConstruct
    public void init() {
		filters = new HashMap<>();
		idProyecto = 0;
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
		int idProject = ((Proyecto) event.getObject()).getProyectoId();
		proyecto = getPizarronMB().getProyectoService().fidProyecto(idProject);
		treeProj = new DefaultTreeNode("Root", null);
		
		for(HistoriasUsuario historia: proyecto.getHistoriasUsuarios()){
			
			TreeNode node = new DefaultTreeNode(historia.getNombreHistoria()
					+" "+historia.getDescripcionHistoria(), treeProj);
			
			for(TareasHistoria tarea: historia.getTareas()){	
				TreeNode leaf = new DefaultTreeNode(tarea.getNombreTarea(),node);
			}
		}
    }
	
	public PizarronMB getPizarronMB() {
		return pizarronMB;
	}

	public void setPizarronMB(PizarronMB pizarronMB) {
		this.pizarronMB = pizarronMB;
	}

	public List<Proyecto> getProyectoList() {
		return proyectoList;
	}

	public void setProyectoList(List<Proyecto> proyectoList) {
		this.proyectoList = proyectoList;
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

	public LazyDataModel<Proyecto> getProjectLazy() {
		return projectLazy;
	}

	public void setProjectLazy(LazyDataModel<Proyecto> projectLazy) {
		this.projectLazy = projectLazy;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
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

	public TreeNode getTreeProj() {
		return treeProj;
	}

	public void setTreeProj(TreeNode treeProj) {
		this.treeProj = treeProj;
	}

}
