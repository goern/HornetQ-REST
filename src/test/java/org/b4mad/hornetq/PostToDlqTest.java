package org.b4mad.nornetq;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.Link;
import org.hornetq.rest.util.LinkStrategy;
import org.junit.Assert;
import org.junit.Test;


/**
* @author <a href="mailto:goern@b4mad.net">Christoph Görn</a>
* @version $Revision: 1 $
*/
public class PostToDlqTest {
	public static Link getLinkByTitle(LinkStrategy strategy, ClientResponse response, String title) {
		return response.getLinkHeader().getLinkByTitle(title);
	}
	
	@Test
	public void testSuccessFirst() throws Exception {
		ClientRequest request = new ClientRequest("http://localhost:8080/hornetq-rest/queues/jms.queue.DLQ");

		ClientResponse response = request.head();
		Assert.assertEquals("*****", 200, response.getStatus());
		
		Link sender = getLinkByTitle(null, response, "msg-create");
		
		// FIXME TODO complete this test
	}
}