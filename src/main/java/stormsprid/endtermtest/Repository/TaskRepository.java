package stormsprid.endtermtest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stormsprid.endtermtest.Entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
