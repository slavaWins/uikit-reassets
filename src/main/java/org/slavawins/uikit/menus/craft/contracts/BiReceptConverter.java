package org.slavawins.uikit.menus.craft.contracts;

public class BiReceptConverter {
    public final SingleReceptConverver from;
    public final SingleReceptConverver to;

    public BiReceptConverter(SingleReceptConverver from, SingleReceptConverver itemReceptConver) {
        this.from = from;
        to = itemReceptConver;
    }
}
