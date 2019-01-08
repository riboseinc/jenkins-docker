for (aSlave in hudson.model.Hudson.instance.slaves) {

  println('====================');
  println('Agent name: ' + aSlave.name);
  println('Agent class: ' + aSlave.class);
  if (aSlave.class != com.cloudbees.jenkins.plugins.amazonecs.ECSSlave) {
    println("Skip: non-ECS agent");
    continue;
  }

  if (aSlave.getComputer().isOffline()) {
    println('Found offline ECS Agent, shutting down now: ' + aSlave.name);
    aSlave.getComputer().setTemporarilyOffline(true, null);
    aSlave.getComputer().doDoDelete();
  }

}
