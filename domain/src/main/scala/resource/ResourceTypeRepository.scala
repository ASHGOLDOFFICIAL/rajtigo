package org.aulune.rajtigo
package domain
package resource


/** Repository for [[ResourceType]]s.
 *  @tparam F effect type.
 */
trait ResourceTypeRepository[F[_]]:
  /** Persists a resource type.
   *  @param elem resource type to persist.
   *  @return resource type if success, otherwise the violated
   *    [[ResourceTypeConstraint]].
   */
  def persist(
      elem: ResourceType,
  ): F[Either[ResourceTypeConstraint, ResourceType]]

  /** Returns a resource type by its identity, if registered.
   *  @param namespace namespace of the owning service.
   *  @param name resource type name.
   */
  def get(
      namespace: Namespace,
      name: ResourceTypeName,
  ): F[Option[ResourceType]]
