package pl.beone.promena.alfrescojvmconsole.transformer.mirror.jdk.example

import org.alfresco.model.ContentModel.PROP_CONTENT
import org.alfresco.model.ContentModel.TYPE_CONTENT
import org.alfresco.repo.nodelocator.CompanyHomeNodeLocator
import org.alfresco.service.ServiceRegistry
import org.alfresco.service.cmr.repository.NodeRef
import org.alfresco.service.namespace.QName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import pl.beone.promena.alfresco.module.core.applicationmodel.node.toSingleNodeDescriptor
import pl.beone.promena.alfresco.module.core.applicationmodel.retry.noRetry
import pl.beone.promena.alfresco.module.core.contract.transformation.PromenaTransformationExecutor
import pl.beone.promena.alfresco.module.core.contract.transformation.PromenaTransformationManager
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorParameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorTransformation
import java.time.LocalDateTime

class JdkMirrorTransformerExample {

    @Autowired
//    @Qualifier("httpPromenaTransformationExecutor")
    @Qualifier("activeMQPromenaTransformationExecutor")
    private lateinit var promenaTransformationExecutor: PromenaTransformationExecutor

    @Autowired
    private lateinit var promenaTransformationManager: PromenaTransformationManager

    @Autowired
    private lateinit var serviceRegistry: ServiceRegistry

    fun alfresco(): List<NodeRef> =
        transform(jdkMirrorParameters())

    fun `alfresco _ sleep`(): List<NodeRef> =
        transform(jdkMirrorParameters(sleep = 3000))

    fun `alfresco _ throwException`(): List<NodeRef> =
        transform(jdkMirrorParameters(throwException = true))

    private fun transform(parameters: Parameters): List<NodeRef> =
        promenaTransformationExecutor.execute(
            jdkMirrorTransformation(TEXT_PLAIN, parameters),
            createNodeWithExampleContentInNewTransaction().toSingleNodeDescriptor(),
            retry = noRetry()
        ).let { promenaTransformationManager.getResult(it).nodeRefs }

    private fun createNodeWithExampleContentInNewTransaction(): NodeRef =
        serviceRegistry.retryingTransactionHelper.doInTransaction({
            getCompanyHomeNodeRef()
                .createNode()
                .saveContent(TEXT_PLAIN, "example")
        }, false, true)

    private fun NodeRef.createNode(targetType: QName = TYPE_CONTENT, name: String? = null): NodeRef {
        val determinedNamePattern = name ?: LocalDateTime.now().toString().replace(":", "_")

        return serviceRegistry.fileFolderService.create(this, determinedNamePattern, targetType).nodeRef
    }

    private fun NodeRef.saveContent(mediaType: MediaType, content: String, contentProperty: QName = PROP_CONTENT): NodeRef {
        serviceRegistry.contentService.getWriter(this, contentProperty, true).apply {
            mimetype = mediaType.mimeType
            encoding = mediaType.charset.name()
        }.also { it.putContent(content) }

        return this
    }

    private fun getCompanyHomeNodeRef(): NodeRef =
        serviceRegistry.nodeLocatorService.getNode(CompanyHomeNodeLocator.NAME, null, null)
}