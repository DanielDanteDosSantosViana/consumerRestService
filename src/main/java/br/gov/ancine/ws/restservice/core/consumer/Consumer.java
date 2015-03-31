/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/

package br.gov.ancine.ws.restservice.core.consumer;

import java.lang.reflect.ParameterizedType;

import br.gov.ancine.ws.restservice.core.searchParameter.IParameterGET;
import br.gov.ancine.ws.restservice.core.searchParameter.IParameterPOST;


/**
 * @author daniel
 *
 */
public abstract class Consumer<T> {

	protected TypeConsumer<T> typeComsumer;
	
	public Consumer(){
		typeComsumer = new TypeConsumer<T>(getEntityClass());
	}
	
	public TypeConsumer<T> consumer(String target , IParameterGET parameter){
		return typeComsumer.configureGetWithParameter(target, parameter);
		
	}
	public TypeConsumer<T> consumer(String target){
			return typeComsumer.configureGet(target);	
	}
	
	
	public TypeConsumer<T> consumerPost(String target , IParameterPOST parameter){
		return typeComsumer.configurePostWithParameter(target, parameter);
		
	}
	public TypeConsumer<T> consumerPost(String target){
		return typeComsumer.configurePost(target);	
	}
	
	public Class<?> getEntityClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) 
				getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		return  clazz;

	}

}
