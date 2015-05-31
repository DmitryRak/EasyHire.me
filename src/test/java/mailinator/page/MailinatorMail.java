package mailinator.page;

import mailinator.utils.JSonReader;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by dmitry on 30.5.15.
 *
 * An abstract representation of single email message on mailinator.com
 */
public class MailinatorMail {
    private String mainUrl = "https://api.mailinator.com/api";
    private JSonReader reader;
    private String to;
    private String from;
    private String Subject;
    private String messageBody;
    private String id;

    /**
     *
     * @param id - unique message id stamped by mailinator itself
     * @param token - unique token provided for each registered account on mailinator. Is needed for using API
     */
    public MailinatorMail(String id, String token){
        reader = new JSonReader();
        setId(id);
        JSONObject messageJsonFormat= null;
        try {
            messageJsonFormat = reader.readJsonFromUrl(mainUrl + "/email?msgid=" + getId() + "&token=" + token).getJSONObject("data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTo(messageJsonFormat.getString("to"));
        setSubject(messageJsonFormat.getString("subject"));
        setFrom(messageJsonFormat.getString("fromfull"));
        setMessageBody(messageJsonFormat.getJSONArray("parts").getJSONObject(0).getString("body"));
    }
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
