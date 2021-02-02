package test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.ini4j.Ini;
import org.ini4j.Profile;
import org.ini4j.Wini;
import test.Re;
import test.WriteExcel;
import test.SQL;




public class test {
    public static void period(String user, String password) throws Exception {
        Re re = new Re();
        SQL sql = new SQL();
        Session session = Session.getDefaultInstance(new Properties( ));
        Store store = session.getStore("imaps");
        /**
         * 设置账号密码
         */
        store.connect("imap.qq.com", user, password);
//        Folder defaultFolder = store.getDefaultFolder();
//        Folder[] folders = defaultFolder.list();
//
//        for (int i = 0; i < folders.length; i++) {
//            System.out.println(folders[i].getName());
//        }
        Folder Onboarding = store.getFolder( "Onboarding" );
        Folder Termination = store.getFolder( "Termination" );
        if(!Termination.exists()){
            Termination.create(1);
        }
        Onboarding.open( Folder.READ_WRITE );
        Termination.open( Folder.READ_WRITE );
        /**
         * 设置筛选信息
         * qq邮箱不支持奇奇怪怪的term，干
         */
        FlagTerm seen = new FlagTerm(new Flags(Flags.Flag.SEEN),false);
//        // 查找入职未读
        Message[] OnboardingMessages = Onboarding.search(seen);
        Message[] TerminationMessages = Termination.search(seen);
        System.out.println("Onboarding共有未读消息："+OnboardingMessages.length);
        System.out.println("Termination共有未读消息："+TerminationMessages.length);
        // Sort messages from recent to oldest
        Arrays.sort( OnboardingMessages, ( m1, m2 ) -> {
            try {
                return m2.getSentDate().compareTo( m1.getSentDate() );
            } catch ( MessagingException e ) {
                throw new RuntimeException( e );
            }
        } );
        Arrays.sort( TerminationMessages, ( m1, m2 ) -> {
            try {
                return m2.getSentDate().compareTo( m1.getSentDate() );
            } catch ( MessagingException e ) {
                throw new RuntimeException( e );
            }
        } );

        ArrayList<ArrayList<String>> OnboardingAns = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> TerminationAns = new ArrayList<ArrayList<String>>();
        //读取邮件
        for ( Message message : OnboardingMessages ) {
            Multipart multipart = (Multipart) message.getContent();

            int count = multipart.getCount();
            for(int i=0; i<count; i++) {
                // 单个部件     注意：单个部件有可能又为一个Multipart，层层嵌套
                BodyPart part = multipart.getBodyPart(i);
                // 单个部件类型
                String type = part.getContentType().split(";")[0];
                if (type.equals("TEXT/PLAIN")) {    // 纯文本
                    OnboardingAns.add(re.OnboardingRe(part.getContent().toString()));

                }
            }
        }
        //写excel
        WriteExcel writeExcel = new WriteExcel();
        writeExcel.toExcel("Onboarding.xls",OnboardingAns);
        sql.AddOnboarding(OnboardingAns);

        //读取邮件
        for ( Message message : TerminationMessages ) {
            //System.out.println(message.getContent());
            Multipart multipart = (Multipart) message.getContent();
            int count = multipart.getCount();
            for(int i=0; i<count; i++) {
                // 单个部件     注意：单个部件有可能又为一个Multipart，层层嵌套
                BodyPart part = multipart.getBodyPart(i);
                // 单个部件类型
                String type = part.getContentType().split(";")[0];
                if (type.equals("TEXT/PLAIN")) {    // 纯文本

                    TerminationAns.add(re.TerminationRe(part.getContent().toString()));
                }
                if (type.equals("multipart/ALTERNATIVE")){

                    //System.out.println(part.getContent().toString());
                    Multipart mul2 = (Multipart) part.getContent();
                    int count2 = mul2.getCount();
                    for(int j=0; j<count2; j++){
                        BodyPart bodyPart2 = mul2.getBodyPart(j);
                        String type2 = bodyPart2.getContentType().split(";")[0];
                        //System.out.println(type2);
                        if (type2.equals("TEXT/PLAIN")) {    // 纯文本
                            //System.out.println(bodyPart2.getContent().toString());
                            TerminationAns.add(re.TerminationRe(bodyPart2.getContent().toString()));
                            //TerminationAns.add(re.TerminationRe(part.getContent().toString()));
                        }
                    }
                    //System.out.println(count2);
                    //System.out.println(message.getContent().toString());

                }
            }
        }
        //写excel
        writeExcel.toExcel("Termination.xls",TerminationAns);
        sql.AddTermination(TerminationAns);
    }

    public static void main( String[] args ) throws Exception {
        //period();
        Wini ini = new Wini (new File("Setting.ini"));
        Profile.Section section = ini.get("Setting");
        String Time = section.get("Time");
        String user = section.get("User");
        String password = section.get("Password");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    period(user,password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },1,Integer.parseInt(Time)*1000);
    }
}