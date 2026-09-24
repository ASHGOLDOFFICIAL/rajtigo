package org.aulune.rajtigo
package domain
package role

import resource.Resource


/** Repository for [[Role]]s and their grants to users.
 *  @tparam F effect type.
 */
trait RoleRepository[F[_]]:
  /** Creates a role, or updates it if one with the same identity already
   *  exists.
   */
  def upsert(elem: Role): F[Role]

  /** Returns a role by its identity, if registered. */
  def get(role: RoleIdentity): F[Option[Role]]

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

  /** Grants a role to a user.
   *  @param grant role grant, valid against the role's applicable resource
   *    types.
   *  @return `false` if the role isn't registered, `true` otherwise.
   *  @note This method is idempotent.
   */
  def grantRole(grant: RoleGrant): F[Boolean]

  /** Revokes a role from a user.
   *  @param user user whose role will be revoked.
   *  @param role role to be revoked.
   *  @param resource resource the revoked grant was scoped to, `None` for a
   *    global grant.
   *  @return `false` if the role isn't registered, `true` otherwise.
   *  @note This method is idempotent.
   */
  def revokeRole(
      user: UserId,
      role: RoleIdentity,
      resource: Option[Resource],
  ): F[Boolean]
