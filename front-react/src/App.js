import React, {Component} from 'react';
import './App.css';
import {BranchService} from './service/BranchService';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Panel} from 'primereact/panel';
import {Menubar} from 'primereact/menubar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';

import 'primereact/resources/themes/nova-alt/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default class App extends Component {
    constructor() {
        super();
        this.state = {
            visible : false,
            branch: {
                name: null,
                description: null,
                userId: null
            },
            selectedBranch : {

            }
        };
        this.items = [
            {
                label: 'Nuevo',
                icon: 'pi pi-fw pi-plus',
                command : () => {this.showSaveDialog()}
            },
            {
                label: 'Editar',
                icon: 'pi pi-fw pi-pencil',
                command: () => {this.showEditDialog()}
            }
        ];
        this.branchService = new BranchService();
        this.save = this.save.bind(this);
        this.footer = (
            <div>
                <Button label="Guardar" icon="pi pi-check" onClick={this.save()} />
            </div>
        );
    }

    componentDidMount() {
        this.branchService.getAll().then(data => this.setState({branches: data}))
    }

    save() {
        this.branchService.save(this.state.branch).then(data=>{
            this.setState({
                visible : false,
                branch: {
                    name: null,
                    description: null,
                    userId: null
                }
            });
            this.branchService.getAll().then(data => this.setState({branches: data}))
        });
    }

    render() {
        return (
            <div style={{width: '80%', margin: '0 auto', marginTop: '20px'}}>
                <Menubar model={this.items}/>
                <br/>
                <Panel header="Branches">
                    <DataTable value={this.state.branches} paginator={true} rows="10" selectionMode="single"
                               selection={this.state.selectedBranch}
                               onSelectionChange={e => this.setState({selectedBranch: e.value})}>
                        <Column field="id" header="ID"></Column>
                        <Column field="name" header="Name"></Column>
                        <Column field="description" header="Description"></Column>
                        <Column field="user_id" header="User"></Column>
                        <Column field="date_created" header="Date Created"></Column>
                        <Column field="last_updated" header="Date Updated"></Column>
                    </DataTable>
                </Panel>

                <Dialog header="Crear branch" visible={this.state.visible} style={{width: '400px'}} footer={this.footer}
                        modal={true} onHide={() => this.setState({visible: false})}>
                    <form id="branch-form">
                        <span className="p-float-label">
                            <InputText value={this.state.branch.name} style={{width: '100%'}} id="name" onChange={(e) => {
                                let val = e.target.value;
                                this.setState(prevState => {
                                    let branch = Object.assign({}, prevState.branch);
                                    branch.name = val;

                                    return {branch};
                                })
                            }
                            }
                            />
                            <label htmlFor="name">Nombre</label>
                        </span>
                        <br/>

                        <span className="p-float-label">
                            <InputText value={this.state.branch.description} style={{width: '100%'}} id="description"
                                       onChange={(e) => {
                                           let val = e.target.value;
                                           this.setState(prevState => {
                                               let branch = Object.assign({}, prevState.branch);
                                               branch.description = val;

                                               return {branch};
                                           })
                                       }
                                       }
                            />
                            <label htmlFor="description">Descripcion</label>
                        </span>
                        <br/>

                        <span className="p-float-label">
                            <InputText value={this.state.branch.userId} style={{width: '100%'}} id="userId"
                                       onChange={(e) => {
                                           let val = e.target.value;
                                           this.setState(prevState => {
                                               let branch = Object.assign({}, prevState.branch);
                                               branch.userId = val;

                                               return {branch};
                                           })
                                       }
                                       }
                            />
                            <label htmlFor="userId">userId</label>
                        </span>
                    </form>
                </Dialog>

            </div>
        );
    }

    showSaveDialog() {
        this.setState({
            visible: true,
            branch: {
                name: null,
                description: null,
                userid: null
            }
        });
        document.getElementById('branch-form').reset();
    }

    showEditDialog() {
        this.setState({
            visible : true,
            branch : {
                name: this.state.selectedBranch.name,
                description: this.state.selectedBranch.description,
                userid: this.state.selectedBranch.userid
            }
        })
    }

}
