package cn.itcast.up.platform.repo;

import cn.itcast.up.platform.entity.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelPo, Long> {
}
