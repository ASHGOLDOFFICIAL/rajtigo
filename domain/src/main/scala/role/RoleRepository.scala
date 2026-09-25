package org.aulune.rajtigo
package domain
package role


/** Repository for [[Role]]s.
 *
 *  @tparam F effect type.
 */
trait RoleRepository[F[_]]:
  /** Persists a role.
   *  @param elem role to persist.
   *  @return role if success, otherwise the violated [[RoleConstraint]].
   */
  def persist(elem: Role): F[Either[RoleConstraint, Role]]

  /** Returns a role by its identity, if registered. */
  def get(role: RoleIdentity): F[Option[Role]]
