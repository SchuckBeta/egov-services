class CreateUsageType extends React.Component {
  constructor(props) {
    super(props);
    this.state={searchSet:{
    "name":"",
    "description":"",
    "tenantId":tenantId,
    "active":"true"
      }
    }
     this.handleChange=this.handleChange.bind(this);
     this.addOrUpdate=this.addOrUpdate.bind(this);

  }
  handleChange(e,name){
        if(name === "active"){
        this.setState({
          searchSet:{
              ...this.state.searchSet,
              active: !this.state.searchSet.active

          }
        })
      }else{
        this.setState({
          searchSet:{
            ...this.state.searchSet,
            [name]:e.target.value
          }
        })
      }
    }

  close(){
      // widow.close();
      open(location, '_self').close();
  }
  componentDidMount(){
    if(window.opener && window.opener.document) {
         var logo_ele = window.opener.document.getElementsByClassName("homepage_logo");
         if(logo_ele && logo_ele[0]) {
           document.getElementsByClassName("homepage_logo")[0].src = window.location.origin + logo_ele[0].getAttribute("src");
         }
       }
       var type=getUrlVars()["type"];
        var id=getUrlVars()["id"];

        if(getUrlVars()["type"]==="View")
        {
            $("input,select,textarea").prop("disabled", true);
          }

          if(type==="Update"||type==="View")
          {
               this.setState({
                 searchSet:getCommonMasterById("wcms-masters","usagetype","UsageType",id).responseJSON["UsageType"][0]

              })
          }

}


addOrUpdate(e,mode){
  var _this=  this;
  e.preventDefault();
          var tempInfo=Object.assign({},_this.state.searchSet) , type = getUrlVars()["type"];
            var body={
              "RequestInfo":requestInfo,
              "UsageType":tempInfo
            },_this=this;
            if (type == "Update") {
                            $.ajax({
                   url:baseUrl+"/wcms-masters/usagetype/_update/"+ this.state.searchSet.code + "?" +"tenantId=" + tenantId,
                    type: 'POST',
                    dataType: 'json',
                    data:JSON.stringify(body),
                    async: false,
                    contentType: 'application/json',
                    headers:{
                      'auth-token': authToken
                    },
                    success: function(res) {
                      console.log(hii);
                          showSuccess("Usage Type Modified successfully.");
                          // window.location.href = 'app/common/show-usage-type.html?type=Update';
                    },
                    error: function(err) {
                        showError(err);

                    }
                });
            } else {
              $.ajax({
                    url:baseUrl+"/wcms-masters/usagetype/_create",
                    type: 'POST',
                    dataType: 'json',
                    data:JSON.stringify(body),
                    async: false,
                    contentType: 'application/json',
                    headers:{
                      'auth-token': authToken
                    },
                    success: function(res) {
                            showSuccess("Usage Type Created successfully.");
                            _this.setState({searchSet:{
                            name:"",
                            description:"",
                            active:"true",
                            "tenantId": tenantId},
                          })

                    },
                    error: function(err) {
                        showError(err);

                    }
                });
            }
}





  render() {
    let {handleChange,addOrUpdate}=this;
    let {list}=this.state;
    let {name,code,description,active}=this.state.searchSet;
    let mode=getUrlVars()["type"];

    const showActionButton=function() {
      if((!mode) ||mode==="Update")
      {
        return (<button type="submit" className="btn btn-submit">{mode?"Update":"Add"}</button>);
      }
    };




    return (
    <div>
    <h3>{ getUrlVars()["type"] ? titleCase(getUrlVars()["type"]) : "Create"} Usage Type</h3>

    <form onSubmit={(e)=>{addOrUpdate(e,mode)}}>

    <div className="row">
          <div className="col-sm-6">
              <div className="row">
                  <div className="col-sm-6 label-text">
                    <label for=""> Name :<span> * </span></label>
                  </div>
                  <div className="col-sm-6">
                      <input type="text" id="name" name="name" value={name}
                        onChange={(e)=>{  handleChange(e,"name")}} required/>
                  </div>
              </div>
            </div>
            <div className="col-sm-6">
                  <div className="row">
                      <div className="col-sm-6 label-text">
                        <label for=""> Description : </label>
                      </div>
                      <div className="col-sm-6">
              <textarea name="description" id="description" value={description}
              onChange={(e)=>{handleChange(e,"description")}} />

          </div>
          </div>
            </div>

              </div>

                 <div className="row">
                        <div className="col-sm-6">
                            <div className="row">
                                <div className="col-sm-6 label-text">
                                  <label for=""> Active : </label>
                                </div>
                                <div className="col-sm-6">
                                <input type="checkbox" name="active" value="true" checked={active == "true" || active  ==  true}
                             onChange={(e)=>{ handleChange(e,"active")}}/>

                                </div>
                            </div>
                          </div>
                         </div>

                         <div className="text-center">
                    {showActionButton()} &nbsp;&nbsp;
                    <button type="button" className="btn btn-close" onClick={(e)=>{this.close()}}>Close</button>
                    </div>
                    </form>



          </div>
          );
      }
}


ReactDOM.render(
  <CreateUsageType />,
  document.getElementById('root')
);
//
// <button type="submit" className="btn btn-submit">Add</button>
// &nbsp;
