/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageContext;
import com.ibm.watson.assistant.v2.model.SessionResponse;

/**
 *
 * @author d.murillo.porras
 * from https://cloud.ibm.com/apidocs/assistant-v2?code=java
 */
public class watson extends Credenciales {
    private IamAuthenticator authenticator;
    private Assistant assistant;
    private SessionResponse session;

    public watson() {
        // Using Assistant v1
        authenticator = new IamAuthenticator(getAPI_KEY());
        assistant = new Assistant(getVERSION(), authenticator);
        assistant.setServiceUrl(getASSISTANT_URL());

        // Disable SSL verification
        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
            .disableSslVerification(true)
            .build();
        assistant.configureClient(configOptions);
        
        // Create a session
        CreateSessionOptions options = new CreateSessionOptions.Builder(getASSISTANT_ID()).build();
        session = assistant.createSession(options).execute().getResult();
        System.out.println(session);
    }
    
    public MessageResponse getRespuesta(String mensaje) {
        MessageInput input = new MessageInput.Builder()
            .text(mensaje)
            .build();
        MessageOptions messageOptions = new MessageOptions.Builder()
            .assistantId(getASSISTANT_ID())
            .sessionId(session.getSessionId())
            .input(input)
            .build();
        MessageResponse messageResponse = assistant.message(messageOptions).execute().getResult();
        System.out.println(messageResponse);
        return messageResponse;
    }
    
    public MessageResponse getRespuesta(String mensaje, MessageContext contexto) {
        MessageInput input = new MessageInput.Builder()
            .text(mensaje)
            .build();
        MessageOptions messageOptions = new MessageOptions.Builder()
            .assistantId(getASSISTANT_ID())
            .sessionId(session.getSessionId())
            .input(input)
            .context(contexto)
            .build();
        MessageResponse messageResponse = assistant.message(messageOptions).execute().getResult();
        System.out.println(messageResponse);
        return messageResponse;
    }
}

