import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Client } from '../../interfaces/client';
import { MatTableDataSource } from '@angular/material/table';
import { NgForm } from '@angular/forms';

const clientCollection: Client[] = [];

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  name: string = "";
  firstName : string = "";
  lastName: string = "";
  phone: string = "";
  email: string = "";

  phonePattern = '^[0-9]+$';
  emailPattern = '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$';

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'phone', 'actions'];

  dataSource = new MatTableDataSource(clientCollection);

  constructor(private clientService: ClientService) {
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients().subscribe((data) => {
      this.dataSource.data = [];
      clientCollection.length = 0;

      data.forEach(function (client: Client, index: number) {
        clientCollection.push({
          id: client.id,
          firstName: client.firstName,
          lastName: client.lastName,
          phone: client.phone,
          email: client.email
        });
      });

      this.dataSource = new MatTableDataSource(clientCollection);
    });
  }

  onSave(form: NgForm) {
    if (form.invalid) {
      form.control.markAllAsTouched();

      return;
    }

    let client: Client;

    client = {
      id: 0,
      firstName: this.firstName,
      lastName: this.lastName,
      phone: this.phone,
      email: this.email
    }

    this.clientService.saveClient(client).subscribe((data: any[]) => {
      alert("Cliente guardado correctamente.");
      this.clearForm(form);
      this.getClients();
    });
  }

  clearForm(form: NgForm) {
    form.resetForm({
      firstName: '',
      lastName: '',
      phone: '',
      email: ''
    });
  }

  onDelete(client: Client) {
    if (!confirm("Realmente deseas eliminar el cliente?")) {
      return;
    }

    this.clientService.deleteClient(client.id).subscribe(() => {
      alert("Cliente eliminado correctamente.");
      this.getClients();
    });
  }
}
