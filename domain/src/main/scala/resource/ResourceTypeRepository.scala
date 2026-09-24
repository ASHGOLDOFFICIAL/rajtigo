package org.aulune.rajtigo
package domain
package resource


/** Repository for [[ResourceType]]s.
 *  @tparam F effect type.
 */
trait ResourceTypeRepository[F[_]]:
  /** Creates a resource type, or updates its description if one with the same
   *  identity already exists.
   */
  def upsert(elem: ResourceType): F[ResourceType]

  /** Returns a resource type by its identity, if registered.
   *  @param namespace namespace of the owning service.
   *  @param name resource type name.
   */
  def get(
      namespace: Namespace,
      name: ResourceTypeName,
  ): F[Option[ResourceType]]
