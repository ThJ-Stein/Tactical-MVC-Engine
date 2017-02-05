package implementation.model;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class Job {

    public static final String PATH = "script/jobs";

    public static final HashMap<String, Job> JOB_MAP = new HashMap();

    private final StatConstraints constraints;


    static {
        File folder = new File(PATH);

        GroovyShell shell = new GroovyShell();

        try {
            for (File script : folder.listFiles()) {
                shell.run(script, new String[0]);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
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

    public StatConstraints getConstraints() {
        return constraints;
    }

    public static void main(String[] args) {
        System.out.println(JOB_MAP);
    }
}
