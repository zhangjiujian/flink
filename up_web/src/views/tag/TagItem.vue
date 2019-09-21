<template>
  <el-submenu
    class="tag-menu"
    :index="tag.id + ''"
    @click.native="handleClick(tag,$event)"
  >
    <template slot="title">
      <i class="el-icon-s-order"></i>
      <span>{{tag.name}}</span>
    </template>

    <tag-item-group v-if="showSubItems" :pid="tag.id+''"></tag-item-group>
  </el-submenu>
</template>

<script>
export default {
  name: "TagItem",
  props: {
    tag: {
      type: Object,
      default: {}
    }
  },
  components: {
    TagItemGroup: () => import("./TagItemGroup")
  },
  data() {
    return {
      showSubItems: false
    }
  },
  methods: {
    handleClick: function(tag, event) {
      event.stopPropagation()
      this.showSubItems = ! this.showSubItems
      if (tag.level == 3){
        this.$router.push("/tags/four/"+tag.id).catch(err => {})
      }
      if (tag.level == 4){
        console.log(tag)
        this.$router.push("/tags/five/"+tag.id).catch(err => {})
      }
    }
  }
}
</script>

<style>

</style>