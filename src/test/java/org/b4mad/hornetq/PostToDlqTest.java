/*	  This file is part of HornetQ-REST.
 *
 *   HornetQ-REST is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   HornetQ-REST is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

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
		ClientRequest request = new ClientRequest("http://localhost:8080/hornetq-rest/queues/jms.queue.Test");

		ClientResponse response = request.head();
		Assert.assertEquals("*****", 200, response.getStatus());
		
		Link sender = getLinkByTitle(null, response, "create");
		System.out.println(response.toString());
		// FIXME TODO complete this test
	}
}