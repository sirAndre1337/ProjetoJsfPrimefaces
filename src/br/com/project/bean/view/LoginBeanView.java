package br.com.project.bean.view;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionController;
import br.com.srv.interfaces.SvrLogin;

@Controller
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	@Resource
	private SessionController sessionController;
	
	@Resource
	private SvrLogin svrLogin;
	
	@RequestMapping(value = "**/invalidar_session", method = RequestMethod.POST)
	public void invalidarSessionMetodo(HttpServletRequest httpServletRequest) throws Exception {
		
		String userLogadoSessao = null;
		if (httpServletRequest.getUserPrincipal() != null) {
			userLogadoSessao = httpServletRequest.getUserPrincipal().getName();
		}
		
		if (userLogadoSessao == null || (userLogadoSessao != null && userLogadoSessao.trim().isEmpty())) {
			userLogadoSessao = httpServletRequest.getRemoteUser();
		}
		
		if (userLogadoSessao != null && !userLogadoSessao.isEmpty()) {
			sessionController.invalidateSession(userLogadoSessao);
		}
	}
	
	public void invalidar (ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean loggedIn = false;
		FacesMessage message = null;
		
		if(svrLogin.autentico(getUsername(), getPassword())) {
			sessionController.invalidateSession(getUsername());
			loggedIn = true;
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN , "Acesso negado. Login ou senha incorretos.", null);
		}
		
		if(message != null) {
			FacesContext.getCurrentInstance().addMessage("msg", message);
		}
		context.addCallbackParam("loggedIn", loggedIn);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
}
