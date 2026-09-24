package org.aulune.rajtigo
package domain
package resource


/** Type of resource an external service exposes, that permissions can be scoped
 *  to.
 *
 *  @param namespace namespace of the owning service.
 *  @param name resource type name.
 *  @param description human-readable description.
 */
final case class ResourceType private (
    namespace: Namespace,
    name: ResourceTypeName,
    description: Description,
):
  /** Identity of this resource type. */
  lazy val identity: ResourceTypeIdentity =
    ResourceTypeIdentity(namespace, name)

  /** Copies with validation. */
  def update(
      namespace: Namespace = namespace,
      name: ResourceTypeName = name,
      description: Description = description,
  ): Option[ResourceType] =
    ResourceType(namespace = namespace, name = name, description = description)


object ResourceType:
  /** Returns [[ResourceType]] with state validation.
   *  @param namespace namespace of the owning service.
   *  @param name resource type name.
   *  @param description human-readable description.
   */
  def apply(
      namespace: Namespace,
      name: ResourceTypeName,
      description: Description,
  ): Option[ResourceType] = Some(
    new ResourceType(
      namespace = namespace,
      name = name,
      description = description,
    ),
  )

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param namespace namespace of the owning service.
   *  @param name resource type name.
   *  @param description human-readable description.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(
      namespace: Namespace,
      name: ResourceTypeName,
      description: Description,
  ): ResourceType = apply(
    namespace = namespace,
    name = name,
    description = description,
  ).getOrElse(throw IllegalArgumentException())
