/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/

package br.com.ws.restservice.core.consumer;

import java.lang.reflect.ParameterizedType;

import br.com.ws.restservice.core.searchParameter.IParameter;


/**
 * @author daniel
 *
 */
public abstract class Consumer<T> {

	protected TypeConsumer<T> typeComsumer;
	
	public Consumer(){
		typeComsumer = new TypeConsumer<T>(getEntityClass());
	}
	
	public TypeConsumer<T> consumer(String target , IParameter parameter){
		return typeComsumer.configureGetWithParameter(target, parameter);
		
	}
	public TypeConsumer<T> consumer(String target){
			return typeComsumer.configureGet(target);	
	}
	
	
	public TypeConsumer<T> consumerPost(String target , IParameter parameter){
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
