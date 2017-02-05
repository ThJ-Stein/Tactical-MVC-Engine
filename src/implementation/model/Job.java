package implementation.model;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import implementation.model.parsers.JobParser;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class Job {

    public static final String PATH = "data/jobs.json";

    public static final HashMap<String, Job> JOB_MAP = new HashMap();

    private final StatConstraints constraints;

    static {
        File file = new File(PATH);
        JobParser parser = new JobParser(file);
        parser.slurp();
    }

    private final String name;

    private Job(String name, ArrayList<Integer> constraintsMin, ArrayList<Integer> constraintsMax) {
        if (constraintsMin.size() != Stats.howManyStats()) throw new AssertionError();
        if (constraintsMax.size() != Stats.howManyStats()) throw new AssertionError();

        this.name = name;
        this.constraints = new StatConstraints(constraintsMin, constraintsMax);

        if (!constraints.canCreateValidStats()) throw new AssertionError();
    }

    //groovy must use int[] declaration, otherwise the list will be an arraylist of Integer
    public static void createJob(String name, ArrayList<Integer> constraintsMin, ArrayList<Integer> constraintsMax) {
        Job job = new Job(name, constraintsMin, constraintsMax);
        JOB_MAP.put(name.toUpperCase(), job);
    }

    public static Job getJob(String name) {
        assert JOB_MAP.containsKey(name);
        return JOB_MAP.get(name);
    }
    public StatConstraints getConstraints() {
        return constraints;
    }

    }
}
