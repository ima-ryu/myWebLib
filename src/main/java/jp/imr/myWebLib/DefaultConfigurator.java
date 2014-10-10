package jp.imr.myWebLib;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class DefaultConfigurator extends ServerEndpointConfig.Configurator {

	protected static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultConfigurator.class);

	public void modifyHandshake(ServerEndpointConfig sec,
            HandshakeRequest request,
            HandshakeResponse response) {
		LOG.debug(request.getRequestURI().toString());
	}
}