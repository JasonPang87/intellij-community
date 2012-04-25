/*
 * Copyright 2000-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.ether.dependencyView;

import gnu.trove.TIntHashSet;
import gnu.trove.TIntObjectProcedure;
import gnu.trove.TIntProcedure;

import java.io.PrintStream;

/**
 * Created by IntelliJ IDEA.
 * User: db
 * Date: 03.11.11
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
abstract class IntIntMultiMaplet implements Streamable {
  abstract boolean containsKey(final int key);

  abstract TIntHashSet get(final int key);

  abstract void put(final int key, final int value);

  abstract void put(final int key, final TIntHashSet value);

  abstract void replace(final int key, final TIntHashSet value);

  abstract void putAll(IntIntMultiMaplet m);

  abstract void replaceAll(IntIntMultiMaplet m);

  abstract void remove(final int key);

  abstract void removeFrom(final int key, final int value);

  abstract void removeAll(final int key, final TIntHashSet values);

  abstract void close();

  abstract void forEachEntry(TIntObjectProcedure<TIntHashSet> proc);

  abstract void flush(boolean memoryCachesOnly);

  public void toStream(final DependencyContext context, final PrintStream stream) {
    forEachEntry(new TIntObjectProcedure<TIntHashSet>() {
      @Override
      public boolean execute(final int a, final TIntHashSet b) {
        stream.print("  Key: ");
        stream.println(context.getValue(a));
        stream.println("  Values:");

        b.forEach(new TIntProcedure() {
          @Override
          public boolean execute(final int value) {
            stream.print("    ");
            stream.println(context.getValue(value));

            return true;
          }
        });
        stream.println("  End Of Values");
        return true;
      }
    });
  }
}
