package org.aulune.rajtigo
package domain
package resource


/** Reference to a concrete resource instance that a permission grant can be
 *  scoped to.
 *  @param resourceType type of the referenced resource.
 *  @param resourceId id of the referenced resource.
 */
final case class Resource(
    resourceType: ResourceTypeIdentity,
    resourceId: ResourceId,
)
