<template>

  <div class="item-group">
    <tag-item v-for="tag in tags" :key="tag.id" :tag="tag"></tag-item>
  </div>
</template>

<script>
import axios from "axios"
import config from "../../Config"
import TagItem from "./TagItem"

export default {
  name: "TagItemGroup",
  components: {
    TagItem
  },
  props: {
    pid: {
      type: String,
      default: '0'
    }
  },
  mounted() {
    axios
      .get(config.baseApi + "tags?pid=" + this.pid)
      .then(response => {
        this.tags = response.data.data;
      })
      .catch(error => {
        this.$message.error("网络请求异常");
      });
  },
  data() {
    return {
      tags: []
    }
  }
};
</script>

<style lang="scss" scoped>

</style>