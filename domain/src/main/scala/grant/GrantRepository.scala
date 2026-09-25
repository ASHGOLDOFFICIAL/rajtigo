package org.aulune.rajtigo
package domain
package grant


import resource.Resource
import role.RoleIdentity


/** Repository for [[Grant]]s of roles to users.
 *  @tparam F effect type.
 */
trait GrantRepository[F[_]]:
  /** Checks if given user was granted given role.
   *  @param user user.
   *  @param role identity of the required role.
   *  @param resource resource the check is scoped to, `None` to check for a
   *    global grant.
   *  @return `Some(true/false)` if the role is registered, `None` if it isn't.
   */
  def hasRole(
      user: UserId,
      role: RoleIdentity,
      resource: Option[Resource],
  ): F[Option[Boolean]]

  /** Persists a grant.
   *  @param elem grant to persist, valid against the role's applicable resource
   *    types.
   *  @return grant if success, otherwise the violated [[GrantConstraint]].
   *  @note This method is idempotent.
   */
  def persist(elem: Grant): F[Either[GrantConstraint, Grant]]

  /** Revokes a role from a user.
   *  @param user user whose role will be revoked.
   *  @param role role to be revoked.
   *  @param resource resource the revoked grant was scoped to, `None` for a
   *    global grant.
   *  @return `false` if the role isn't registered, `true` otherwise.
   *  @note This method is idempotent.
   */
  def revoke(
      user: UserId,
      role: RoleIdentity,
      resource: Option[Resource],
  ): F[Boolean]
