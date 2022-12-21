package tech.mhuang.pacebox.elasticsearch.model.index.key;

public class SearchParameter {
    /**
     * 类型名称
     */
    public static final String NAME = "search_analyzer";

    /**
     * 可选值
     */
    public enum Values {
        IkMaxWord("ik_max_word"), IkSmart("ik_smart"), Standard("standard");
        private final String value;

        Values(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
