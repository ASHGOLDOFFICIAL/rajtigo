package org.aulune.rajtigo
package domain
package permission

import resource.ResourceTypeIdentity


/** Permission that can be granted to users.
 *  @param namespace namespace of permission.
 *  @param name permission name.
 *  @param description human-readable description.
 *  @param applicableResourceTypes resource types this permission can be scoped
 *    to. Empty means the permission is global.
 */
final case class Permission private (
    namespace: Namespace,
    name: PermissionName,
    description: Description,
    applicableResourceTypes: Set[ResourceTypeIdentity],
):
  /** Identity of this permission. */
  def identity: PermissionIdentity = PermissionIdentity(namespace, name)

  /** Copies with validation. */
  def update(
      namespace: Namespace = namespace,
      name: PermissionName = name,
      description: Description = description,
      applicableResourceTypes: Set[ResourceTypeIdentity] =
        applicableResourceTypes,
  ): Option[Permission] = Permission(
    namespace = namespace,
    name = name,
    description = description,
    applicableResourceTypes = applicableResourceTypes,
  )


object Permission:
  /** Returns [[Permission]] with state validation.
   *  @param namespace permission namespace.
   *  @param name permission name.
   *  @param description human-readable description.
   *  @param applicableResourceTypes resource types this permission can be
   *    scoped to, empty if the permission is global-only.
   */
  def apply(
      namespace: Namespace,
      name: PermissionName,
      description: Description,
      applicableResourceTypes: Set[ResourceTypeIdentity],
  ): Option[Permission] = Some(
    new Permission(
      namespace = namespace,
      name = name,
      description = description,
      applicableResourceTypes = applicableResourceTypes,
    ),
  )

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param namespace permission namespace.
   *  @param name permission name.
   *  @param description human-readable description.
   *  @param applicableResourceTypes resource types this permission can be
   *    scoped to, empty if the permission is global-only.
   *  @throws IllegalArgumentException if given params are invalid.
   */
  def unsafe(
      namespace: Namespace,
      name: PermissionName,
      description: Description,
      applicableResourceTypes: Set[ResourceTypeIdentity],
  ): Permission = apply(
    namespace = namespace,
    name = name,
    description = description,
    applicableResourceTypes = applicableResourceTypes,
  ).getOrElse(throw IllegalArgumentException())
