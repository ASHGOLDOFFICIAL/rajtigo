package org.aulune.rajtigo
package domain
package permission


/** Constraints that exist on permissions as a collection. */
enum PermissionConstraint:
  /** Namespace and name pair should be unique. */
  case UniqueIdentity
