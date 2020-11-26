package com.sanjay.androidamcservice.utils;


import com.sanjay.androidamcservice.chat.service.XMPP;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.IOException;


public class ConnectionUtils {

    XMPPTCPConnection connection = null;

    public XMPPTCPConnection getXmptcConnection() {

        if (XMPP.getInstance().isConnected()) {
            try {
                connection = XMPP.getInstance().getConnection();
            } catch (XMPPException | SmackException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                connection = XMPP.getInstance().getConnection();
            } catch (XMPPException | SmackException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
