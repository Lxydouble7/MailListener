package test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import test.WriteExcel;

class Re {

    public ArrayList<String> TerminationRe(String text){
        String LastDayofWork = "(Last\\sDay\\sof\\sWork:\\s)(.*)";
        Pattern p = Pattern.compile(LastDayofWork);
        Matcher m = p.matcher(text);
        ArrayList<String> targets = new ArrayList<String>();
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String TerminationDate = "(Termination Date: )(.*)";
        p = Pattern.compile(TerminationDate);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }

        String EmployeeID  = "(Employee ID: )(.*)";
        p = Pattern.compile(EmployeeID);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            EmployeeID  = "(Employee ID )(.*)";
            p = Pattern.compile(EmployeeID);
            m = p.matcher(text);
            if (m.find()){
                targets.add(m.group(2));
            }
            else{
                targets.add("");
            }
        }


        String LegalName = "(Legal Name: )(.*)";
        p = Pattern.compile(LegalName);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }

        String Brand = "(Brand: )(.*)";
        p = Pattern.compile(Brand);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }


        String WorkLocation = "(Work Location: )(.*)";
        p = Pattern.compile(WorkLocation);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }


        String WorkerType = "(Worker Type: )(.*)";
        p = Pattern.compile(WorkerType);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }

        String WorkerSub_Type = "(Worker Sub-Type: )(.*)";
        p = Pattern.compile(WorkerSub_Type);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }

        String Department = "(Department: )(.*)";
        p = Pattern.compile(Department);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }

        String LineManager = "(Line Manager: )(.*)";
        p = Pattern.compile(LineManager);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        String PositionTitle = "(Position Title:\s)(.*)";
        p = Pattern.compile(PositionTitle);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String BusinessTitle = "(Business Title:\s)(.*)";
        p = Pattern.compile(BusinessTitle);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        System.out.println(targets);
        return targets;

    }


    public ArrayList<String> OnboardingRe(String text) {

        String HireDate = "(Hire\\sDate:\\s)(.*)";
        Pattern p = Pattern.compile(HireDate);
        Matcher m = p.matcher(text);
        ArrayList<String> targets = new ArrayList<String>();
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String WorkerID = "(Worker ID: )(.*)";
        p = Pattern.compile(WorkerID);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String LegalName = "(Legal\sName:\s)(.*)";
        p = Pattern.compile(LegalName);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String Brand = "(Brand:\s)(.*)";
        p = Pattern.compile(Brand);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String City = "(City:\s)(.*)";
        p = Pattern.compile(City);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String WorkerType = "(Worker Type:\s)(.*)";
        p = Pattern.compile(WorkerType);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String WorkerSub_Type = "(Worker Sub-Type:\s)(.*)";
        p = Pattern.compile(WorkerSub_Type);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String SupervisoryOrganization = "(Supervisory Organization:\s)(.*)";
        p = Pattern.compile(SupervisoryOrganization);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);


        String Department = "(Department:\s)(.*)";
        p = Pattern.compile(Department);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String LineManager = "(Line Manager:\s)(.*)";
        p = Pattern.compile(LineManager);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);


        String PositionTitle = "(Position Title:\s)(.*)";
        p = Pattern.compile(PositionTitle);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        //System.out.println(targets);

        String BusinessTitle = "(Business Title:\s)(.*)";
        p = Pattern.compile(BusinessTitle);
        m = p.matcher(text);
        if (m.find()){
            targets.add(m.group(2));
        }
        else{
            targets.add("");
        }
        System.out.println(targets);
        return targets;
    }


}

