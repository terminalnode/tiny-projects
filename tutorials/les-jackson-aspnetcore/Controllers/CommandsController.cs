using System.Collections.Generic;
using Commander.Data;
using Commander.Models;
using Microsoft.AspNetCore.Mvc;

namespace Commander.Controllers
{
  [Route("api/[controller]")] // /api/commands
  [ApiController]
  public class CommandsController : ControllerBase
  {
    private readonly MockCommanderRepo _repository = new MockCommanderRepo();

    [HttpGet] // GET /api/commands
    public ActionResult<IEnumerable<Command>> GetAllCommands()
    {
      var commandItems = _repository.GetAllCommands();
      return Ok(commandItems);
    }

    [HttpGet("{id}")] // GET /api/commands/{id}
    public ActionResult<Command> GetCommandById(int id)
    {
      var commandItem = _repository.GetCommandById(id);
      return Ok(commandItem);
    }
  }
}