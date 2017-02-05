package implementation.model.parsers

import groovy.json.JsonSlurper
import implementation.model.Job

/**
 * Created by thomas on 5-2-17.
 */
class JobParser {

    JsonSlurper slurper
    File file

    JobParser(File file) {
        this.file = file
        this.slurper = new JsonSlurper()
    }

    void slurp() {
        def object = slurper.parse(file)
        object.jobs.each {

            String name = it.name
            ArrayList<Integer> min = it.min
            ArrayList<Integer> max = it.max

            Job.createJob(name, min, max)
        }
    }
}
