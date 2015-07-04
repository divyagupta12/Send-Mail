/**
 * Created by DIVYA on 6/24/2015.
 */


import com.beust.jcommander.Parameter;
public class Arguments {

    @Parameter(names = "-from", description = "recipients name", required = true)
    public String from;

    @Parameter(names = "-to", description = "senders name", required = true)
    public String to;

    @Parameter(names = "-subject", description = "subject", required = true)
    public String subject;

    @Parameter(names = "-content", description = "content", required = true)
    public String content;


    @Parameter(names = "-attachment", description = "path of file to be attached", required =true)
    public String  attachment;


    public String pass;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {

        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {

        this.to = to;
    }

    public String getSubject() {

        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
