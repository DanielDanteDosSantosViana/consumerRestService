/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.ws.restservice.core.searchParameter;

import java.util.Map;

public interface IParameter {
	 void putParameter(String key, Object value);
	 
	 Map<String, Object> getParameter();
	 
	 void cleanMap();
	 
}
