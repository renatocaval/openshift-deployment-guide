# Deploying Lightbend applications to OpenShift

This guide describes how to deploy Akka, Lagom and Play applications to OpenShift.

It is intended to be used by people who have a cursory understanding of OpenShift or Kubernetes - you should know what Kubernetes and OpenShift are, have a basic understanding of what a pod, service, container is, you should have interacted with the `oc` or `kubectl` commands before. Though you are not expected to be an expert.

While this guide is targeted at OpenShift, much of it will be applicable to Kubernetes in general. Where the guide depends on OpenShift specific features, we will generally mention this. Although this guide uses the OpenShift client command, `oc`, in most cases it can be substituted with `kubectl`, since `oc` for the most part provides a superset of commands support by `kubectl`.

## Following this guide

This guide covers how to deploy applications built using multiple Lightbend projects, namely, Akka, Lagom and Play. For your particular use case, you may only be using one of these, and so the parts of the guide specific to the other projects will not be relevant. The guide tries to maintain a smooth flow of content regardless of which technology you're using.

In addition to the multiple project, there are multiple different configurations that this guide documents, such as, using Scala and using Java, using Maven and using sbt, and using a relational database or using Cassandra. It is up to you to follow the parts that are relevant to you, and skip over the parts that are not.

We recommend that you follow along with this guide, deploying our sample applications to OpenShift as you go. Ideally, you should follow along using a realistic OpenShift cluster, something deployed to AWS for example, as this will provide a more realistic demonstration of the technologies, allowing you to see many services running across a cluster. However, due to the hosting cost of running such a cluster, this may not always be feasible, and perhaps you are just evaluating these technologies with no budget for hosting them yet. In that case, you can follow this guide using Minishift, running on your local machine. Running this guide in Minishift has some significant limitations, primarily around resources such as memory and CPU. In some cases, you will have to deploy things with only one replica, when in production you should really use at least three. And often you will have to assign only small fractions of CPU resources to an application, especially if you are running many, and this will make the application very slow to start up.

## Installing OpenShift

We will not actually document installing OpenShift or Minishift in this guide, since there are already resources on the web for doing this. You may already have an OpenShift installation that you can use, in which case, you can simply use that.

### Installing a full cluster

If you wish to install a full OpenShift cluster from scratch, you can follow one of the following:
 
* [OKD](https://docs.okd.io/latest/getting_started/administrators.html) - These are instructions for installing OKD, the open source distribution of OpenShift.
* [OpenShift Container Platform](https://docs.openshift.com/container-platform/latest/getting_started/install_openshift.html) - These are instructions for installing OpenShift Container Platform, RedHats commercially supported OpenShift distribution. It requires a RedHat license to run it.

In this guide, we will assume that you have created a project/namespace called `myproject`, and will use this as the default namespace that all applications get deployed to. You can either create this project yourself, or use a different project, but be careful to ensure that all commands and configuration files that reference `myproject` are updated to use the project you create.

You will need to ensure that you set your environment up to be able to push docker images to your OpenShift installation. This requires exposing your OpenShift installations internal registry to the outside world, logging in, and then setting the `DOCKER_HOST` environment variable to your OpenShift docker registries hostname or IP address. For more information, see [here](https://docs.openshift.com/container-platform/3.11/dev_guide/managing_images.html#accessing-the-internal-registry) and [here](https://blog.openshift.com/remotely-push-pull-container-images-openshift/).

### Installing Minishift

Minishift can be installed following [these instructions](https://docs.okd.io/latest/minishift/getting-started/installing.html).

Before starting Minishift, we recommend you configure it to allow it more RAM, and potentially more CPU (by default, it gets 2GB of RAM and 2 CPUs). If you use Minishift for other purposes, you may want to create a custom profile so that you don't interfere with these, a profile called `lightbend` can be created and switched to by running:

```
minishift profile set lightbend
```

Now you can configure how much RAM should be allocated to Minishift, let's allocate it 8GB:

```
minishift config set memory 8GB
```

The memory can also be set by passing an argument when you start Minishift, but doing it this way, you ensure that whenever you delete and restart your Minishift instance, the config is remembered.

Now you can start Minishift:

```
minishift start
```

Once Minishift is started, you need to ensure that the `oc` binary is on your path, and that your environment is configured to be able to push to Minishifts internal docker registry. To do this, run:

```
eval $(minishift oc-env)
eval $(minishift docker-env)
```

The first command modifies your `PATH` to ensure the `oc` binary is on it, the second sets some `DOCKER_*` environment variables to tell Docker which host to use and how to authenticate with it when pushing images. These commands will need to be rerun every time you open a new terminal window, or any time you delete and then restart your Minishift instance.

@@@ index

* [Deploying a Lagom application to OpenShift](lagom/index.md)

@@@

@@toc { depth=1 }