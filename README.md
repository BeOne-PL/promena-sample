# Promena Sample
All Promena images use [`mirror-jdk`](./transformer/mirror-jdk) transformer and [`promena-connector-http`](https://github.com/BeOne-PL/promena/tree/master/module/connector/activemq) connector module. 
You can perform a transformation in any sample using [Promena#IntelliJ plugin](https://github.com/BeOne-PL/promena#intellij-plugin) on [`mirror-jdk/example`](./transformer/mirror-jdk/example) (it contains the examples in Java and Kotlin).

## Deployment
### [Manual](./deployment/manual)
This deployment shows how to run Promena in a cluster without `Kubernetes` or `OpenShift`. It means that you have to specify `akka.cluster.seed-nodes` property manually (see [Joining to Seed Nodes](https://doc.akka.io/docs/akka/2.5.26/cluster-usage.html#joining-to-seed-nodes) for more details). The seed node has to start first. It is done by waiting for the seed node `promena-connector-http` connector module to be run by the worker nodes. It is only for demonstration purposes and shouldn't be used in production. 

This sample uses [`promena-file-http`](./image/promena-file-http) Promena image with [`promena-connector-http`](https://github.com/BeOne-PL/promena/tree/master/module/connector/activemq) connector module. It also demonstrates how to set [`promena-communication-file`](https://github.com/BeOne-PL/promena/tree/master/module/communication/file) communication module - `communication.file.internal.directory.path` (see [`docker-compose.yml`](./deployment/manual/docker-compose.yml)) property indicates the same location in the volume that is mounted on all Promena nodes.

Use [`start.bin`](./deployment/manual/bin/start.sh) to build Promena image and start containers, [`logs.bin`](./deployment/manual/bin/logs.sh) to see logs, [`stop.bin`](./deployment/manual/bin/stop.sh) to stop containers and [`clean.bin`](./deployment/manual/bin/clean.sh) to delete volumes.

It isn't the recommended way to deploy Promena.

### [Kubernetes](./deployment/kubernetes) & [OpenShift](./deployment/openshift)
Generally, they have common elements. The only difference is that [Kubernetes](./deployment/kubernetes) uses [`Deployment`](./deployment/kubernetes/deployment.yaml) and [OpenShift](./deployment/openshift) uses [`DeploymentConfig`](./deployment/openshift/deployment-config.yaml).

Unlike [Manual](./deployment/manual), you don't have to specify seed nodes manually. This kind of deployment uses [`promena-cluster-management-kubernetes`](https://github.com/BeOne-PL/promena/tree/master/module/cluster/management/kubernetes) module - it chooses leader dynamically and you don't have to worry.

 These samples use [`promena-file-http-kubernetes`](./image/promena-file-http-kubernetes) Promena image with [`promena-connector-http`](https://github.com/BeOne-PL/promena/tree/master/module/connector/activemq) connector module and [`promena-cluster-management-kubernetes`](https://github.com/BeOne-PL/promena/tree/master/module/cluster/management/kubernetes) module. It also demonstrates how to set [`promena-communication-file`](https://github.com/BeOne-PL/promena/tree/master/module/communication/file) communication module but it is based on the local volume. If your cluster contains more than one node you have to provide a volume that is accessible on all nodes.
 
#### Kubernetes
```
kubectl apply -f namespace.yaml
kubectl apply -f cluster-role.yaml
kubectl apply -f cluster-role-binding.yaml
kubectl apply -f persistent-volume.yaml
kubectl apply -f persistent-volume-claim.yaml
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

#### OpenShift
```
oc apply -f namespace.yaml
oc apply -f cluster-role.yaml
oc apply -f cluster-role-binding.yaml
oc apply -f persistent-volume.yaml
oc apply -f persistent-volume-claim.yaml
oc apply -f deployment-config.yaml
oc apply -f service.yaml
```

## Transformer
You can generate the template of a transformer using archetype (see [Promena - Development Guide](https://github.com/BeOne-PL/promena/blob/master/DEVELOPMENT-GUIDE.md) for more details). 

[`appender-jdk-generic`](./transformer/appender-jdk-general) demonstrates the common case when you don't want to deal with specific communication implementation. See [`Processor`](./transformer/appender-jdk-general/implementation/src/main/kotlin/pl/beone/promena/transformer/appender/jdkgeneral/processor/Processor.kt).

[`appender-jdk-specific`](./transformer/appender-jdk-specific) demonstrates how to deal with specific communication. For example, for [`promena-communication-file`](https://github.com/BeOne-PL/promena/tree/master/module/communication/file) communication module, it operates directly on the file. See [`Processor`](./transformer/appender-jdk-specific/implementation/src/main/kotlin/pl/beone/promena/transformer/appender/jdkspecific/processor/Processor.kt).

## Module
You can generate the template of a module using archetype (see [Promena - Development Guide](https://github.com/BeOne-PL/promena/blob/master/DEVELOPMENT-GUIDE.md) for more details). 

[`Promena-cluster-listener`](./module/promena-cluster-listener) demonstrates 2 cases:
* How to subscribe Cluster Events - [`MemberClusterListener`](./module/promena-cluster-listener/internal/src/main/kotlin/pl/beone/promena/cluster/listener/MemberClusterListener.kt)
* How to use Cluster Metrics Extension - [`MetricsClusterListener`](./module/promena-cluster-listener/internal/src/main/kotlin/pl/beone/promena/cluster/listener/MetricsClusterListener.kt), [`module-promena-cluster-listener.properties`](./module/promena-cluster-listener/configuration/src/main/resources/module-promena-cluster-listener.properties)

## Alfresco
These samples demonstrate how to integrate Alfresco with Promena. This folder also contains [`alfresco-jvm-console-mirror-jdk-example`](./alfresco/alfresco-jvm-console-mirror-jdk-example) that allows you to perform a transformation using Promena using Alfresco dynamically. It requires [Alfresco JVM Console - IntelliJ plugin](https://github.com/Skotar/alfresco-jvm-console).

Visit [Promena Alfresco](https://github.com/BeOne-PL/promena-alfresco) for more details about modules.

Use `start.bin` to build Promena image and start containers, `logs.bin` to see logs, `stop.bin` to stop containers and `clean.bin` to delete volumes.

### Communication
Both Promena images in these samples use [`promena-connector-http`](https://github.com/BeOne-PL/promena/tree/master/module/connector/http) connector module. Alfresco Content Services is the client side and it has to use [`alfresco-promena-connector-http`](https://github.com/BeOne-PL/promena-alfresco/tree/master/connector/alfresco-promena-connector-http) module to integrate with Promena HTTP connector (with the following properties in `alfresco-global.properties`):
```properties
promena.connector.http.host=promena
promena.connector.http.port=8080
```
Alfresco Content Services also uses [`alfresco-promena-core`](https://github.com/BeOne-PL/promena-alfresco/tree/master/alfresco-promena-core) module that is the crucial module. 
 
#### [Memory](./alfresco/communication/memory)
In order to use [`promena-communication-memory`](https://github.com/BeOne-PL/promena/tree/master/module/communication/memory) communication module, you have to specify in [`alfresco-global.properties`](./alfresco/communication/memory/data/alfresco-global.properties):
```properties
promena.core.communication.external.id=memory
```
No additional properties in Promena are required.

#### [File](./alfresco/communication/file)
In order to use [`promena-communication-file`](https://github.com/BeOne-PL/promena/tree/master/module/communication/file) communication module, you have to specify in [`alfresco-global.properties`](./alfresco/communication/file/data/alfresco-global.properties):
```properties
promena.core.communication.external.id=file
promena.core.communication.external.file.directory.path=/tmp/promena-communication
```
Property `promena.core.communication.external.file.directory.path` and `communication.file.internal.directory.path` (see [`docker-compose.yml`](./alfresco/communication/file/docker-compose.yml)) have to indicate the same location (it was described in [Manual](#manual) section).

### Connector
Sample [`http-activemq`](./alfresco/connector/http-activemq) demonstrates how to use [`alfresco-promena-connector-http`](https://github.com/BeOne-PL/promena-alfresco/tree/master/connector/alfresco-promena-connector-http) module and [`alfresco-promena-connector-activemq`](https://github.com/BeOne-PL/promena-alfresco/tree/master/connector/alfresco-promena-connector-activemq) module simultaneously. 

It also uses [`promena-communication-memory`](https://github.com/BeOne-PL/promena/tree/master/module/communication/memory) communication module.

Integration with HTTP Promena connector was described in one of the previous sections. Integration with ActiveMQ Promena connector requires to specify the following property in [`alfresco-global.properties`](./alfresco/connector/http-activemq/data/alfresco-global.properties):
```properties
messaging.broker.url=failover:(nio://activemq:61616)?timeout=3000&jms.useCompression=true
```

You also have to specify broker url in Promena (see [`docker-compose.yml`](./alfresco/connector/http-activemq/docker-compose.yml)):
```properties
spring.activemq.broker-url=failover:(nio://activemq:61616)?timeout=3000&jms.useCompression=true
```

Each connector provides its own [`PromenaTransformationExecutor`](https://github.com/BeOne-PL/promena-alfresco/blob/master/alfresco-promena-core/src/main/kotlin/pl/beone/promena/alfresco/module/core/contract/transformation/PromenaTransformationExecutor.kt) implementation so if you want to inject a bean, you have to specify one of them.

### Rendition
There are two samples - the first one for Alfresco Content Services 6.1.2 ([`predefined-rendition_6.1.2`](./alfresco/rendition/predefined-rendition_6.1.2) module) and the second one for Alfresco Content Services 6.2.0 ([`predefined-rendition_6.2.0`](./alfresco/rendition/predefined-rendition_6.2.0) module). They do exactly the same thing - replace the standard Alfresco rendition system with the equivalent in Promena environment. This functionality is provided by [`alfresco-promena-rendition_6.1.2`](https://github.com/BeOne-PL/promena-alfresco/tree/master/rendition/alfresco-promena-rendition_6.1.2) and [`alfresco-promena-rendition_6.2.0`](https://github.com/BeOne-PL/promena-alfresco/tree/master/rendition/alfresco-promena-rendition_6.2.0) modules.

Additionally, they use [`alfresco-promena-predefined-rendition`](https://github.com/BeOne-PL/promena-alfresco/tree/master/rendition/alfresco-promena-predefined-rendition) module to provide `avatar32`, `avatar`, `imgpreview`, `doclib`, `medium` and `pdf` renditions (see [Promena Alfresco - Development Guide](https://github.com/BeOne-PL/promena-alfresco/blob/master/DEVELOPMENT-GUIDE.md) to find out how to write own rendition). If you want to check how it works, just upload a document to the repository using Alfresco Share.

These samples also demonstrate how to increase concurrency level within a single instance. On Alfresco Content Services side, you have to increase the concurrency level of a connector. In case of ActiveMQ, set in `alfresco-global.properties`:
```properties
promena.connector.activemq.spring.jms.listener.concurrency=4
promena.connector.activemq.spring.jms.listener.max-concurrency=4
```
On Promena side, increase the number of actors of transformers (see `docker-compose.yml`):
```properties
transformer.pl.beone.promena.transformer.converter.libreoffice.LibreOfficeConverterTransformer.actors=4
transformer.pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer.actors=4
```

They use [`promena-file-activemq-predefined-rendition`](./image/promena-file-activemq-predefined-rendition) Promena image with [`promena-connector-activemq`](https://github.com/BeOne-PL/promena-alfresco/tree/master/connector/alfresco-promena-connector-activemq) connector module and [`promena-communication-file`](https://github.com/BeOne-PL/promena/tree/master/module/communication/file) communication module that were described in one of the previous sections, [`converter-libreoffice`](https://github.com/BeOne-PL/promena-transformer-converter-libreoffice) and [`converter-imagemagick`](https://github.com/BeOne-PL/promena-transformer-converter-imagemagick) transformers for renditions.

## Image
Folder [`image`](./image) contains several examples of projects that provide Promena Docker image for this guide. Open their `pom.xml` files to see some inclusions of modules and transformers.

## Constructing transformation
A transformation can be constructed in two ways:
* Without any dependency:
```kotlin
fun transformation(): Transformation =
    singleTransformation("mirror", TEXT_PLAIN, emptyParameters() + ("sleep" to 2000)) next
            singleTransformation("mirror", TEXT_PLAIN, emptyParameters() + ("sleep" to 3000))
```
* With `application-model` dependency of the specific transformer (less loose coupling):
```kotlin
fun transformation(): Transformation =
    jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(sleep = 2000)) next
            jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(sleep = 3000))
```