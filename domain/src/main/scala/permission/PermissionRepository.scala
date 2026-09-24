package org.aulune.rajtigo
package domain
package permission

import resource.Resource


/** Repository for [[Permission]]s.
 *
 *  Permissions themselves are never granted directly: users hold roles, and a
 *  permission check resolves through whatever roles a user was granted. See
 *  `role.RoleRepository`.
 *
 *  @tparam F effect type.
 */
trait PermissionRepository[F[_]]:
  /** Creates a permission, or updates its description if one with the same
   *  identity already exists.
   */
  def upsert(elem: Permission): F[Permission]

  /** Returns a permission by its identity, if registered. */
  def get(permission: PermissionIdentity): F[Option[Permission]]

  /** Checks if given user effectively has given permission, through any role
   *  they were granted.
   *  @param user user.
   *  @param permission identity of a required permission.
   *  @param resource resource the check is scoped to, `None` to check for a
   *    global grant.
   *  @return `Some(true/false)` if the permission is registered, `None` if it
   *    isn't.
   */
  def hasPermission(
      user: UserId,
      permission: PermissionIdentity,
      resource: Option[Resource],
  ): F[Option[Boolean]]
