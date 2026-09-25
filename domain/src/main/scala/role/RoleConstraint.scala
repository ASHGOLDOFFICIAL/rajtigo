package org.aulune.rajtigo
package domain
package role


/** Constraints that exist on roles as a collection. */
enum RoleConstraint:
  /** Namespace and name pair should be unique. */
  case UniqueIdentity
