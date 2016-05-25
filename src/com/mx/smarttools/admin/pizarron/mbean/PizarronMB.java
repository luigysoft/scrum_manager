package com.mx.smarttools.admin.pizarron.mbean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.smarttools.admin.pizarron.service.EsfuerzoService;
import com.mx.smarttools.admin.pizarron.service.EstatusTareaService;
import com.mx.smarttools.admin.pizarron.service.HistoriaService;
import com.mx.smarttools.admin.pizarron.service.ProyectoService;
import com.mx.smarttools.admin.pizarron.service.TareaService;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;

@ViewScoped
@ManagedBean
@Component
public class PizarronMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private TareaService tareaService;
	@Autowired
	private EstatusTareaService estatusTareaService;
	@Autowired
	private HistoriaService historiaService;
	@Autowired
	private EsfuerzoService esfuerzoService;
	
	private DashboardModel pizarron;
	private Proyecto proyecto;
	private Integer idProy;
	private Dashboard dashboard;
	
	@PostConstruct
	public void init() {
		idProy = Integer.valueOf(1);
//		proyecto = proyectoService.fidProyecto(idProy);
		
		pizarron = new DefaultDashboardModel();
		
		DashboardColumn historias = new DefaultDashboardColumn();
	    DashboardColumn pendientes = new DefaultDashboardColumn();
	    DashboardColumn trabajando = new DefaultDashboardColumn();
	    DashboardColumn terminadas = new DefaultDashboardColumn();
	    
	    pizarron.addColumn(historias);
	    pizarron.addColumn(pendientes);
	    pizarron.addColumn(trabajando);
	    pizarron.addColumn(terminadas);
	    
	    pendientes.addWidget("pendiente0");
	    pendientes.addWidget("pendiente1");
	    pendientes.addWidget("pendiente2");
	    pendientes.addWidget("pendiente3");
	    pendientes.addWidget("pendiente4");
	    pendientes.addWidget("pendiente5");
	    pendientes.addWidget("pendiente6");
	    pendientes.addWidget("pendiente7");
	    pendientes.addWidget("pendiente8");
	    pendientes.addWidget("pendiente9");
	    
	    trabajando.addWidget("trabajando0");
	    trabajando.addWidget("trabajando1");
	    
	    terminadas.addWidget("terminada0");
	    terminadas.addWidget("terminada1");
	 }

	public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
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
    
    public DashboardModel getPizarron() {
		return pizarron;
	}

	public void setPizarron(DashboardModel pizarron) {
		this.pizarron = pizarron;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getIdProy() {
		return idProy;
	}

	public void setIdProy(Integer idProy) {
		this.idProy = idProy;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public ProyectoService getProyectoService() {
		return proyectoService;
	}

	public void setProyectoService(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public TareaService getTareaService() {
		return tareaService;
	}

	public void setTareaService(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	public EstatusTareaService getEstatusTareaService() {
		return estatusTareaService;
	}

	public void setEstatusTareaService(EstatusTareaService estatusTareaService) {
		this.estatusTareaService = estatusTareaService;
	}

	public HistoriaService getHistoriaService() {
		return historiaService;
	}

	public void setHistoriaService(HistoriaService historiaService) {
		this.historiaService = historiaService;
	}

	public EsfuerzoService getEsfuerzoService() {
		return esfuerzoService;
	}

	public void setEsfuerzoService(EsfuerzoService esfuerzoService) {
		this.esfuerzoService = esfuerzoService;
	}
}
