/**
 * Copyright (c) 2012, md_5. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.md_5.specialsource.provider;

import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.Collection;
import net.md_5.specialsource.Jar;

/**
 * Lookup inheritance from a class given a jar.
 */
public class JarProvider implements InheritanceProvider {

    private final Jar self;

    public JarProvider(Jar self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "JarProvider{" +
                "self=" + self +
                '}';
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<String> getParents(String owner) {
        ClassNode node = self.getNode(owner);
        if (node == null) {
            return null;
        }

        Collection<String> parents = new ArrayList<String>();
        for (String iface : node.interfaces) {
            parents.add(iface);
        }
        if (node.superName != null) {
            parents.add(node.superName);
        }

        return parents;
    }
}
