package com.mx.smarttools.admin.pizarron.mbean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.LazyDataModel;
import com.mx.smarttools.admin.pizarron.mbean.lazy.ProyectoDataModel;
import com.mx.smarttools.admin.proyecto.model.EstatusTareas;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@ViewScoped
@ManagedBean
public class ProyectoMB {

	@ManagedProperty(value="#{pizarronMB}")
	private PizarronMB pizarronMB;
	
	private Map<String, Object> filters;
	private int idProyecto;
	private String nombreProyecto;
	
	private List<Proyecto> proyectoList;
	private LazyDataModel<Proyecto> projectLazy;
	
	private Integer idProy;
	private DashboardModel pizarron;
	private Dashboard dashboard;
	Proyecto proyecto;
	Proyecto selectedProject;
	
	private final String ESTATUS_PENDIENTE = "pendiente";
	private final String ESTATUS_TRABAJANDO = "trabajando";
	private final String ESTATUS_TERMINADA = "terminada";
	
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
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		
		idProy = ((Proyecto) event.getObject()).getProyectoId();
		
		proyecto = pizarronMB.getProyectoService().fidProyecto(idProy);
		
		dashboard = (Dashboard) application.createComponent(fc, 
				"org.primefaces.component.Dashboard", 
				"org.primefaces.component.DashboardRenderer");
        
		dashboard.setId("dashboard");
		
	    DashboardColumn historias = new DefaultDashboardColumn();
	    DashboardColumn pendientes = new DefaultDashboardColumn();
	    DashboardColumn trabajando = new DefaultDashboardColumn();
	    DashboardColumn terminadas = new DefaultDashboardColumn();
	    
	    DashboardModel model = new DefaultDashboardModel();
	    
	    model.addColumn(historias);
	    model.addColumn(pendientes);
	    model.addColumn(trabajando);
	    model.addColumn(terminadas);
	    
	    dashboard.setModel(model);
	    
	    Panel panelHst0 = (Panel) application.createComponent(fc, 
    			"org.primefaces.component.Panel", 
    			"org.primefaces.component.PanelRenderer");
	    panelHst0.setId("UH0");
	    panelHst0.setHeader("Historias");
//	    panelHst0.setClosable(true);
//	    panelHst0.setToggleable(true);
	    panelHst0.setStyleClass("dsh_panel_hst_size");
    	
		getDashboard().getChildren().add(panelHst0);
	    historias.addWidget("UH0");

	    
	    Panel panelPendient0 = (Panel) application.createComponent(fc, 
    			"org.primefaces.component.Panel", 
    			"org.primefaces.component.PanelRenderer");
	    panelPendient0.setId("pendiente0");
	    panelPendient0.setHeader("Pendiente");
	    panelPendient0.setStyleClass("dsh_panel_tsk_size");
    	
		getDashboard().getChildren().add(panelPendient0);
		pendientes.addWidget("pendiente0");
	    
	    
	    Panel panelTrabaj0 = (Panel) application.createComponent(fc, 
    			"org.primefaces.component.Panel", 
    			"org.primefaces.component.PanelRenderer");
	    panelTrabaj0.setId("trabajando0");
	    panelTrabaj0.setHeader("En proceso");
	    panelTrabaj0.setStyleClass("dsh_panel_tsk_size");
    	
		getDashboard().getChildren().add(panelTrabaj0);
		trabajando.addWidget("trabajando0");
	    
	    
	    Panel panelTemin0 = (Panel) application.createComponent(fc, 
    			"org.primefaces.component.Panel", 
    			"org.primefaces.component.PanelRenderer");
	    panelTemin0.setId("terminada0");
	    panelTemin0.setHeader("Hecho");
	    panelTemin0.setStyleClass("dsh_panel_tsk_size");
	    
	    getDashboard().getChildren().add(panelTemin0);
		terminadas.addWidget("terminada0");
		
	    for(HistoriasUsuario histUs: proyecto.getHistoriasUsuarios()){
	    	
	    	Panel panel = (Panel) application.createComponent(fc, 
	    			"org.primefaces.component.Panel", 
	    			"org.primefaces.component.PanelRenderer");
	    	panel.setId(histUs.getNombreHistoria());
	    	panel.setHeader(histUs.getNombreHistoria());
			panel.setStyleClass("dsh_panel_hst_size");
	    	
			getDashboard().getChildren().add(panel);
	    	historias.addWidget(histUs.getNombreHistoria());
	    	
	    	HtmlOutputText text = new HtmlOutputText();
			text.setValue(histUs.getDescripcionHistoria());
			panel.getChildren().add(text);
			
			
			for(TareasHistoria tarHist: histUs.getTareas()){
				Panel panelTar = (Panel) application.createComponent(fc, 
		    			"org.primefaces.component.Panel", 
		    			"org.primefaces.component.PanelRenderer");
				
				if(tarHist.getEstatus().equals(ESTATUS_PENDIENTE)){
//					panelTar.setId("pendiente"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
//					pendientes.addWidget("pendiente"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
					panelTar.setId(ESTATUS_PENDIENTE+tarHist.getTareaId());
					pendientes.addWidget(ESTATUS_PENDIENTE+tarHist.getTareaId());
					
				}else if(tarHist.getEstatus().equals(ESTATUS_TRABAJANDO)){
//					panelTar.setId("trabajando"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
//					trabajando.addWidget("trabajando"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
					panelTar.setId(ESTATUS_TRABAJANDO+tarHist.getTareaId());
					trabajando.addWidget(ESTATUS_TRABAJANDO+tarHist.getTareaId());
					
				}else if(tarHist.getEstatus().equals(ESTATUS_TERMINADA)){
//					panelTar.setId("terminada"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
//					terminadas.addWidget("terminada"+histUs.getNombreHistoria()+"Tar"+tarHist.getNumeroTarea());
					panelTar.setId(ESTATUS_TERMINADA+tarHist.getTareaId());
					terminadas.addWidget(ESTATUS_TERMINADA+tarHist.getTareaId());
				}
				
				panelTar.setHeader(histUs.getNombreHistoria()+" Tarea "+tarHist.getNumeroTarea());
				panelTar.setStyleClass("dsh_panel_tsk_size");
				
				getDashboard().getChildren().add(panelTar);
				
				HtmlOutputText textTar = new HtmlOutputText();
				textTar.setValue(tarHist.getDescripcionTarea());
				textTar.setStyleClass("dsh_panel_tsk_fnt_size");
				panelTar.getChildren().add(textTar);
			}
	    }
		
	}
	
	public void handleReorder(DashboardReorderEvent event) {
//      FacesMessage message = new FacesMessage();
//      message.setSeverity(FacesMessage.SEVERITY_INFO);
//      message.setSummary("Reordered: " + event.getWidgetId());
//      message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        
		int taskId = 0;
        String estatus = ESTATUS_PENDIENTE;
        
//      column index 1 = PENDIENTE 	= pendiente 
//      column index 2 = EN PROCESO	= trabajando
//      column index 3 = HECHO		= terminada
        
        if(event.getWidgetId().startsWith(ESTATUS_PENDIENTE)){
        	taskId = Integer.parseInt(event.getWidgetId().replace(ESTATUS_PENDIENTE, ""));
        }else if(event.getWidgetId().startsWith(ESTATUS_TRABAJANDO)){
        	taskId = Integer.parseInt(event.getWidgetId().replace(ESTATUS_TRABAJANDO, ""));
        }else if(event.getWidgetId().startsWith(ESTATUS_TERMINADA)){
        	taskId = Integer.parseInt(event.getWidgetId().replace(ESTATUS_TERMINADA, ""));
        }
        
//      Realiza movimientos cuando la columna de la que se va a la que llega es diferente
        if(!event.getColumnIndex().equals(event.getSenderColumnIndex())){
        	if(event.getColumnIndex() == 1){
        		estatus = ESTATUS_PENDIENTE;
        	}else if(event.getColumnIndex() == 2){
        		estatus = ESTATUS_TRABAJANDO;
        	}else if(event.getColumnIndex() == 3){
        		estatus = ESTATUS_TERMINADA;
        	}
        }
        
        TareasHistoria task = pizarronMB.getTareaService().findTarea(taskId);
        task.setEstatus(estatus);
        
        System.out.println("Tarea de historia Id: "+task.getTareaId()+", estatus al que cambiara: "+task.getEstatus());
        
        try {
			pizarronMB.getTareaService().updateTarea(task);
			
			// Agrega registro en estatus tarea.
			addEstatusTarea(task);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         
//        addMessage(message);
    }
	
	private void addEstatusTarea(TareasHistoria t) throws Exception{
		
		EstatusTareas estatusTa =  null;
		int lastIndxEstatus = 0;
		
		if(t.getTareaId() != 0){
			estatusTa = new EstatusTareas();
			
			lastIndxEstatus = pizarronMB.getEstatusTareaService().getLastId();
			
			estatusTa.setEstatusTareasId(lastIndxEstatus+1);
			estatusTa.setTareaFk(t.getTareaId());
			estatusTa.setEstatus(t.getEstatus());
			estatusTa.setFechaRegistro(new Date());
			estatusTa.setUsuarioRegistro("lsanchez");
		}
		
		pizarronMB.getEstatusTareaService().saveEstatusTarea(estatusTa);
		
	}
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
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

	public PizarronMB getPizarronMB() {
		return pizarronMB;
	}

	public void setPizarronMB(PizarronMB pizarronMB) {
		this.pizarronMB = pizarronMB;
	}

	public Integer getIdProy() {
		return idProy;
	}

	public void setIdProy(Integer idProy) {
		this.idProy = idProy;
	}

	public DashboardModel getPizarron() {
		return pizarron;
	}

	public void setPizarron(DashboardModel pizarron) {
		this.pizarron = pizarron;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Proyecto getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Proyecto selectedProject) {
		this.selectedProject = selectedProject;
	}
}
