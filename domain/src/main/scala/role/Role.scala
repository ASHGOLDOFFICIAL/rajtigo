package org.aulune.rajtigo
package domain
package role


import permission.PermissionIdentity
import resource.ResourceTypeIdentity


/** Bundle of permissions that can be granted to users as a unit.
 *  @param namespace namespace of the owning service.
 *  @param name role name.
 *  @param description human-readable description.
 *  @param permissions permissions this role grants. Every one of them must
 *    belong to this role's own namespace.
 *  @param resourceTypes resource types this role can be scoped to when granted.
 *    Empty means the role is global-only.
 */
final case class Role private (
    namespace: Namespace,
    name: RoleName,
    description: Description,
    permissions: Set[PermissionIdentity],
    resourceTypes: Set[ResourceTypeIdentity],
):
  /** Identity of this role. */
  lazy val identity: RoleIdentity = RoleIdentity(namespace, name)

  /** Copies with validation. */
  def update(
      namespace: Namespace = namespace,
      name: RoleName = name,
      description: Description = description,
      permissions: Set[PermissionIdentity] = permissions,
      resourceTypes: Set[ResourceTypeIdentity] = resourceTypes,
  ): Option[Role] = Role(
    namespace = namespace,
    name = name,
    description = description,
    permissions = permissions,
    resourceTypes = resourceTypes,
  )


object Role:
  /** Returns [[Role]] if every permission belongs to the given namespace.
   *  @param namespace namespace of the owning service.
   *  @param name role name.
   *  @param description human-readable description.
   *  @param permissions permissions this role grants, all of which must belong
   *    to `namespace`.
   *  @param resourceTypes resource types this role can be scoped to, empty if
   *    the role is global-only.
   */
  def apply(
      namespace: Namespace,
      name: RoleName,
      description: Description,
      permissions: Set[PermissionIdentity],
      resourceTypes: Set[ResourceTypeIdentity],
  ): Option[Role] =
    val ownsEveryPermission = permissions.forall(_.namespace == namespace)
    Option.when(ownsEveryPermission)(
      new Role(
        namespace = namespace,
        name = name,
        description = description,
        permissions = permissions,
        resourceTypes = resourceTypes,
      ),
    )

  /** Unsafe constructor to use inside always-valid boundary.
   *  @param namespace namespace of the owning service.
   *  @param name role name.
   *  @param description human-readable description.
   *  @param permissions permissions this role grants, all of which must belong
   *    to `namespace`.
   *  @param resourceTypes resource types this role can be scoped to, empty if
   *    the role is global-only.
   *  @throws IllegalArgumentException if a permission doesn't belong to
   *    `namespace`.
   */
  def unsafe(
      namespace: Namespace,
      name: RoleName,
      description: Description,
      permissions: Set[PermissionIdentity],
      resourceTypes: Set[ResourceTypeIdentity],
  ): Role = apply(
    namespace = namespace,
    name = name,
    description = description,
    permissions = permissions,
    resourceTypes = resourceTypes,
  ).getOrElse(throw IllegalArgumentException())
