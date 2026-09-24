package org.aulune.rajtigo
package domain
package resource


/** Identity of a resource type.
 *
 *  @param namespace namespace of the owning service.
 *  @param name resource type name.
 */
final case class ResourceTypeIdentity(
    namespace: Namespace,
    name: ResourceTypeName,
)
