package mailinator.page;

import mailinator.utils.JSonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 30.5.15.
 *
 * Class for handy interaction with mailinator using native API
 */
public class Mailinator {
    private JSonReader reader;
    private String mainUrl = "https://api.mailinator.com/api";
    private String token;

    /**
     *
     * @param token - unique id to use mailinator.com API. Can be retrieved from your profile->Settings
     */
    public Mailinator(String token) {
        this.token = token;
        reader = new JSonReader();
    }
    //TODO make public with MailinatorMail objects
    private ArrayList<MailinatorMail> readAllMessagesFromInbox(String inbox){
        JSONArray jsonMessages = null;
        ArrayList<MailinatorMail> messages = new ArrayList<>();
        try {
            jsonMessages =  reader.readJsonFromUrl(mainUrl+"/inbox?to="+inbox+"&token="+token).getJSONArray("messages")  ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < jsonMessages.length() ; i++){
            JSONObject messageJSonFormat = jsonMessages.getJSONObject(i);
                MailinatorMail email = new MailinatorMail(messageJSonFormat.getString("id"), token);
                messages.add(email);
        }
        return messages;
    }
    public ArrayList<MailinatorMail> getMessagesFrom(String inbox, String from){
        ArrayList<MailinatorMail> allMessages = readAllMessagesFromInbox(inbox);
        ArrayList<MailinatorMail> messages = new ArrayList<>();
        for(int i = 0 ; i < allMessages.size() ; i++){
            MailinatorMail email = allMessages.get(i);
            if(email.getFrom().equals(from)){
                messages.add(email);
            }
        }
        return messages;
    }
    public ArrayList<MailinatorMail> getMessagesBySubject(String inbox, String subject){
        ArrayList<MailinatorMail> allMessages = readAllMessagesFromInbox(inbox);
        ArrayList<MailinatorMail> messages = new ArrayList<>();
        for(int i = 0 ; i < allMessages.size() ; i++){
            MailinatorMail email = allMessages.get(i);
            if(email.getSubject().equals(subject)){
                messages.add(email);
            }
        }
        return messages;
    }
}
