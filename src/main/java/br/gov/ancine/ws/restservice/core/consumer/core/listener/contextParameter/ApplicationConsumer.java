package br.gov.ancine.ws.restservice.core.consumer.core.listener.contextParameter;

import br.gov.ancine.ws.restservice.core.consumer.core.scan.Scan;
import br.gov.ancine.ws.restservice.core.consumer.core.scan.ScanConsumer;

public class ApplicationConsumer {
	private Scan scan = new ScanConsumer();
	
	public void scan(String packageConsumers) {
		scan.findConsumer();
		scan.findErrorResponse();		
	}


}
