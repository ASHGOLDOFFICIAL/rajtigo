package org.aulune.rajtigo
package domain
package resource


/** Constraints that exist on resource types as a collection. */
enum ResourceTypeConstraint:
  /** Namespace and name pair should be unique. */
  case UniqueIdentity
