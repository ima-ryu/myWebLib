package jp.imr.myWebLib;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.apache.tomcat.websocket.pojo.PojoEndpointServer;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDefaultEndpoint {

	protected String uri = "ws://localhost:8080/myWebLib/default";

	@Test
	public void testConnect() throws DeploymentException, URISyntaxException {
        WebSocketContainer wsContainer = ContainerProvider.getWebSocketContainer();
        ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.create(TestDefaultEndpoint.class, uri).configurator(new DefaultConfigurator());

		assertNotNull(wsContainer);
		assertNotNull(builder);

		// TODO: アプリのWebSocketエンドポイントを立ててテストする方法の検討
		//Session session1 = new PojoEndpointServer().onOpen(session, endpointConfig);
		//Session session1 = new WsWebSocketContainer().connectToServer(TestDefaultEndpoint.class, new URI(uri));
	}
}
