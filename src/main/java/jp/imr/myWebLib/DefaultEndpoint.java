package jp.imr.myWebLib;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocket Server Endpoint.
 * @author ima-ryu
 */
@ServerEndpoint(
		value = "/default",
		configurator = DefaultConfigurator.class
		)
public class DefaultEndpoint {
	
	protected static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultEndpoint.class);
	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
	public static final long TIMEOUT = TimeUnit.MINUTES.toMillis(30);

	public DefaultEndpoint() {
		LOG.debug("construct: {}", toString());
	}
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		LOG.debug("open: {}", session);
		session.setMaxIdleTimeout(TIMEOUT);
        session.getBasicRemote().sendText("onOpen");
        peers.add(session);
	}

	@OnClose
	public void onClose(Session session,  CloseReason reason) {
		LOG.debug("close: {}", session);
		peers.remove(session);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		LOG.error("error session: ", session);
		LOG.error("error message: ", t);
	}

	@OnMessage
	public void onMessage(String text, Session session) {
		LOG.debug("message: [{}] from {}", text, session);
		sendMessage(text);
	}

	public static void sendMessage(String text) {
		LOG.debug("message: [{}] to all", text);
		for (Session s : peers) {
			s.getAsyncRemote().sendText(text);
		}
	}
}
