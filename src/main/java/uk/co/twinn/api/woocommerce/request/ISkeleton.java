/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

public interface ISkeleton {

        public String endPoint();

        public String toJson();

        public interface Creator<T extends Creator<T>>{}

        public interface Reader<T extends Reader<?>>{}

        public interface Updater<T extends Updater<T>> extends Creator<T> {}

        public interface Deleter<T extends Deleter<T>> extends Reader<T> {}

        public interface Batcher<T extends Batcher>{}

        public interface ListAll<T extends ListAll> {}

        public interface Searcher<T extends Searcher> {}

}
