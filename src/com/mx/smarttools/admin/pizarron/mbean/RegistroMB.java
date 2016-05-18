package com.mx.smarttools.admin.pizarron.mbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.mx.smarttools.admin.common.Constantes;
import com.mx.smarttools.admin.common.utils.FechaUtils;
import com.mx.smarttools.admin.proyecto.model.EstatusTareas;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@ViewScoped
@ManagedBean
public class RegistroMB {

	@ManagedProperty(value="#{pizarronMB}")
	private PizarronMB pizarronMB;
	
	private String nombreProyecto;
	private String objetivoProyecto;
	private Date fechaInicio;
	private Date fechaTermino;
	
	private boolean skip;
	
	private String historiaUsr;
	private List<HistoriasUsuario> histUsrList;
	
	private String nombreHistoria;
	private int numeroHistoria;
	private String nombreTarea;
	private String descTarea;
	private List<TareasHistoria> tareasList;
	
	private List<SelectItem> historiasSelItList;
	
	private final String NOMBRE_HISTORIA = "HU";
	private final int DEFAULT_SPRINT = 0;
	private final int DEFAULT_PROJECT = 0;
	
	private TreeNode treeProj;
	
	@PostConstruct
    public void init() {
        histUsrList = new ArrayList<HistoriasUsuario>();
        historiasSelItList = new ArrayList<SelectItem>();
        tareasList = new ArrayList<TareasHistoria>();
        treeProj = new DefaultTreeNode("Root", null);
    }
	
	public void saveProject(){
		Proyecto proy = new Proyecto();
		int lastIndxProy = 0;
		
		proy.setNombreProyecto(nombreProyecto);
		proy.setObjetivo(objetivoProyecto);
		proy.setFechaInicio(fechaInicio);
		proy.setFechaTermino(fechaTermino);
		
		proy = addSystemValues(proy);
		
		try {
			lastIndxProy = pizarronMB.getProyectoService().getLastId();
			proy.setProyectoId(lastIndxProy+1);
			
			getPizarronMB().getProyectoService().saveProyecto(proy);
			
			if(histUsrList != null){
				if(!histUsrList.isEmpty()){
					int lastIndxHst = 0;
					
					for (int i = 0; i < histUsrList.size(); i++) {
						HistoriasUsuario hstTmp = (HistoriasUsuario) histUsrList.get(i);
						hstTmp.setProyectoFk(proy.getProyectoId());
						hstTmp = addSystemValues(hstTmp);
						
						histUsrList.set(i, hstTmp);
					}
					
					for (int i = 0; i < histUsrList.size(); i++) {
						HistoriasUsuario hstTmp = (HistoriasUsuario) histUsrList.get(i);
						lastIndxHst = pizarronMB.getHistoriaService().getLastId();
						hstTmp.setHistoriaId(lastIndxHst+1);
						
						System.out.println("Historia[ Id: " + hstTmp.getHistoriaId()
								+ " proyectoId: " + hstTmp.getProyectoFk()
								+ " nombre: " + hstTmp.getNombreHistoria()
								+ " descripcion: " + hstTmp.getDescripcionHistoria()
								+ " consecutivo: " + hstTmp.getConsecutivo()
								+ " sprintId: " + hstTmp.getEsfuerzoFk()
								+ " fechaReg: " + FechaUtils.getStringDateAsFormat(
										hstTmp.getFechaRegistro(), "dd/MM/yyyy") 
								+ " usuarioReg: " + hstTmp.getUsuarioRegistro()
								+" ]");
						
						pizarronMB.getHistoriaService().saveHistoria(hstTmp);
						int tareaNoByHist = 1;
						
						if(tareasList != null){
							if(!tareasList.isEmpty()){
								int lastIndxTar = 0;
								
								for(ListIterator<TareasHistoria> taskListIt = tareasList.listIterator(); taskListIt.hasNext();){
									TareasHistoria taskTmp = taskListIt.next();
									
									if(taskTmp.getHistoriaFk() == Integer.parseInt(hstTmp.
											getNombreHistoria().replace(Constantes.NOMBRE_HISTORIA, ""))){
										
										taskTmp.setHistoriaFk(hstTmp.getHistoriaId());
										
										lastIndxTar = pizarronMB.getTareaService().getLastId();
										taskTmp.setTareaId(lastIndxTar+1);
										taskTmp.setNumeroTarea(tareaNoByHist);
										taskTmp.setConsecutivo(tareaNoByHist);
										
										getPizarronMB().getTareaService().saveTarea(taskTmp);
										addEstatusTarea(taskTmp);
										
										taskListIt.set(taskTmp);
										tareaNoByHist++;
									}
								}
								for(int j=0; j < tareasList.size(); j++){
									TareasHistoria taHist = tareasList.get(j);
									System.out.println("Tarea[ Id: " + taHist.getTareaId()
									+ " historiaId: " + taHist.getHistoriaFk()
									+ " numero: " + taHist.getNumeroTarea()
									+ " nombre: " + taHist.getNombreTarea()
									+ " descripcion: " + taHist.getDescripcionTarea()
									+ " consecutivo: " + taHist.getConsecutivo()
									+ " estatus: " + taHist.getEstatus()
									+ " colaboradorId: " + taHist.getColaboradorFk()
									+ " fechaReg: " + FechaUtils.getStringDateAsFormat(
											taHist.getFechaRegistro(), "dd/MM/yyyy") 
									+ " usuarioReg: " + taHist.getUsuarioRegistro()
									+" ]");
								}
							}
						}
					}					
				}
			}
			
			addMessage(FacesMessage.SEVERITY_INFO, "Registro de proyecto exitoso", 
					"El proyecto "+proy.getNombreProyecto()+" se registro exitosamente.");
			
			cleanForm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Registro de proyecto provoco error", "Error al registrar el proyecto");
		}
	}
	
	public void cleanForm(){
		nombreProyecto = null;
		objetivoProyecto = null;
		fechaInicio = null;
		fechaTermino = null;
	}
	
	private Proyecto addSystemValues(Proyecto proyTmp){
		proyTmp.setFechaRegistro(new Date());
		proyTmp.setUsuarioRegistro("lsanchez");		
		return proyTmp;
	}
	
	public void addHistoria(){
		HistoriasUsuario hstUsr = getHistUsr(historiaUsr);
		
		if(hstUsr != null){
			histUsrList.add(hstUsr);
			updateHistSelList(hstUsr);
			TreeNode node = new DefaultTreeNode(hstUsr.getNombreHistoria()
					+" "+hstUsr.getDescripcionHistoria(), treeProj);
			historiaUsr = null;
		}else{
			addMessage(FacesMessage.SEVERITY_ERROR, 
					"Agregar Historia provoco error", "Error al agregar Historia de Usuario");
		}
	}
	
	public void updateHistSelList(HistoriasUsuario hstUsr){
		historiasSelItList.add(new SelectItem(
				hstUsr.getNombreHistoria(),hstUsr.getDescripcionHistoria()));
	}
	
	private HistoriasUsuario getHistUsr(String strHistUsr){
		HistoriasUsuario hstUsr = null;
		int indxHstList = 0;
		
		if(!histUsrList.isEmpty()){
			indxHstList = histUsrList.size();
		}
		indxHstList = indxHstList + 1;
		
		if(strHistUsr != null){
			hstUsr = new HistoriasUsuario();
			hstUsr.setDescripcionHistoria(strHistUsr);
			hstUsr.setNombreHistoria(NOMBRE_HISTORIA+indxHstList);
			hstUsr.setConsecutivo(indxHstList);
			hstUsr.setProyectoFk(DEFAULT_PROJECT);
			hstUsr.setEsfuerzoFk(DEFAULT_SPRINT);
		}
		return hstUsr;
	}
	
	private HistoriasUsuario addSystemValues(HistoriasUsuario hist){
		hist.setFechaRegistro(new Date());
		hist.setUsuarioRegistro("lsanchez");		
		return hist;
	}
	
	public void addTarea(){
		TareasHistoria tarea = getTarea(nombreHistoria, nombreTarea, descTarea);
		
		if(tarea != null){
			tareasList.add(tarea);
			
			List<TreeNode> leafList = treeProj.getChildren();
			
			for(TreeNode leaf: leafList){
				String aux = (String) leaf.getData();
				if(aux.startsWith(nombreHistoria)){
					TreeNode node = new DefaultTreeNode(tarea.getNombreTarea(),leaf);
					
				}
			}
			nombreHistoria = null;
			nombreTarea = null;
			descTarea = null;
			
		}else{
			addMessage(FacesMessage.SEVERITY_ERROR, 
					"Agregar Tarea provoco error", "Error al agregar Tarea");
		}
	}
	
	private TareasHistoria getTarea(String nombreHst, String nombreTar, String descTar){
		TareasHistoria tarItem = new TareasHistoria();
		int indxTarList = 0;
		
		if(!tareasList.isEmpty()){
			indxTarList = tareasList.size();
		}
		
		indxTarList = indxTarList + 1;
		
		tarItem.setNombreTarea(nombreTar);
		tarItem.setDescripcionTarea(descTar);
		tarItem.setNumeroTarea(indxTarList);
		tarItem.setConsecutivo(indxTarList);
		tarItem.setEstatus(
				Constantes.ESTATUS_PENDIENTE);
		tarItem.setColaboradorFk(
				Constantes.DEFAULT_COLABORADOR);
		tarItem.setFechaRegistro(new Date());
		tarItem.setUsuarioRegistro("lsanchez");
		
		tarItem.setHistoriaFk(Integer.parseInt(
				nombreHst.replace(Constantes.NOMBRE_HISTORIA, "")));
		return tarItem;
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
	
	public void addMessage(Severity severity, String summary, String detail){
		FacesMessage message = new FacesMessage();
        message.setSeverity(severity);
        message.setSummary(summary);
        message.setDetail(detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
	public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 

	public PizarronMB getPizarronMB() {
		return pizarronMB;
	}

	public void setPizarronMB(PizarronMB pizarronMB) {
		this.pizarronMB = pizarronMB;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getObjetivoProyecto() {
		return objetivoProyecto;
	}

	public void setObjetivoProyecto(String objetivoProyecto) {
		this.objetivoProyecto = objetivoProyecto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String getHistoriaUsr() {
		return historiaUsr;
	}

	public void setHistoriaUsr(String historiaUsr) {
		this.historiaUsr = historiaUsr;
	}

	public List<HistoriasUsuario> getHistUsrList() {
		return histUsrList;
	}

	public void setHistUsrList(List<HistoriasUsuario> histUsrList) {
		this.histUsrList = histUsrList;
	}

	public String getNombreHistoria() {
		return nombreHistoria;
	}

	public void setNombreHistoria(String nombreHistoria) {
		this.nombreHistoria = nombreHistoria;
	}

	public int getNumeroHistoria() {
		return numeroHistoria;
	}

	public void setNumeroHistoria(int numeroHistoria) {
		this.numeroHistoria = numeroHistoria;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getDescTarea() {
		return descTarea;
	}

	public void setDescTarea(String descTarea) {
		this.descTarea = descTarea;
	}

	public List<TareasHistoria> getTareasList() {
		return tareasList;
	}

	public void setTareasList(List<TareasHistoria> tareasList) {
		this.tareasList = tareasList;
	}

	public List<SelectItem> getHistoriasSelItList() {
		return historiasSelItList;
	}

	public void setHistoriasSelItList(List<SelectItem> historiasSelItList) {
		this.historiasSelItList = historiasSelItList;
	}

	public TreeNode getTreeProj() {
		return treeProj;
	}

	public void setTreeProj(TreeNode treeProj) {
		this.treeProj = treeProj;
	}
}
