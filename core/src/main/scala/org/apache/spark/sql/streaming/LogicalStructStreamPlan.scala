/*
 * Copyright (c) 2017 SnappyData, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */
package org.apache.spark.sql.streaming

import org.apache.spark.sql.catalyst.analysis.MultiInstanceRelation
import org.apache.spark.sql.catalyst.expressions.Attribute
import org.apache.spark.sql.catalyst.plans.logical.{LogicalPlan, Statistics}

import scala.collection.immutable


case class LogicalStructStreamPlan(child: LogicalPlan,
                                   options: Any)
  extends LogicalPlan with MultiInstanceRelation {

  def newInstance(): LogicalStructStreamPlan =
    LogicalStructStreamPlan(child, options).asInstanceOf[this.type]

  @transient override lazy val statistics = Statistics(
    0// sizeInBytes = BigInt(session.sessionState.conf.defaultSizeInBytes)
  )
  override def output : Seq[Attribute] = {
    child.output
  }

  println(options)

  def children: immutable.Nil.type = Nil
}